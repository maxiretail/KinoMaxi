package com.ministren.kinomaxi.ui.film.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.ministren.kinomaxi.R
import com.ministren.kinomaxi.databinding.FragmentFilmDetailsBinding
import com.ministren.kinomaxi.di.ViewModelFactory
import com.ministren.kinomaxi.entity.Film
import com.ministren.kinomaxi.ui.film.frames.FilmFramesAdapter

class FilmDetailsFragment : Fragment() {

    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    private val filmViewModel: FilmViewModel by activityViewModels() { ViewModelFactory() }
    private val filmFramesAdapter = FilmFramesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filmFramesView.adapter = filmFramesAdapter

        binding.root.setOnRefreshListener {
            filmViewModel.loadFilmById(FILM_IDS.random())
        }

        filmViewModel.state.observe(viewLifecycleOwner, this::showNewState)

        if (filmViewModel.state.value == null) {
            filmViewModel.loadFilmById(FILM_IDS.random())
        }
    }

    private fun showNewState(state: FilmViewState) {
        when (state) {
            FilmViewState.Loading -> {
                binding.root.isEnabled = false
                binding.root.isRefreshing = false
                binding.loaderView.visibility = View.VISIBLE
                binding.contentView.visibility = View.GONE
                binding.errorView.visibility = View.GONE
            }
            FilmViewState.Error -> {
                binding.root.isEnabled = true
                binding.root.isRefreshing = false
                binding.loaderView.visibility = View.GONE
                binding.contentView.visibility = View.GONE
                binding.errorView.visibility = View.VISIBLE
            }
            is FilmViewState.Loaded -> {
                binding.root.isEnabled = true
                binding.root.isRefreshing = false
                binding.loaderView.visibility = View.GONE
                binding.contentView.visibility = View.VISIBLE
                binding.errorView.visibility = View.GONE
                showFilmInfo(state.film)
            }
            FilmViewState.Refreshing -> {
                binding.root.isEnabled = true
                binding.root.isRefreshing = true
            }
        }
    }

    private fun showFilmInfo(film: Film) {
        with(binding.filmDetailsLayout) {
            filmGenres.text = film.genres.joinToString(separator = ", ")
            filmNameRus.text = film.nameRus
            filmNameEng.text = film.nameEng
            filmSlogan.text = film.slogan
            filmYear.text = film.year
            filmLength.text = getString(R.string.film_length_value, film.length)
        }

        binding.filmDescription.text = film.description

        with(binding.filmPosterLayout) {
            filmAgeRating.text = getString(R.string.film_age_rating, film.ageRating)
            Glide.with(this@FilmDetailsFragment).load(film.posterUrl).into(filmPoster)
        }

        filmFramesAdapter.setItems(film.frames)
    }

    private companion object {
        val FILM_IDS = listOf<Long>(
            1253633, 279091, 1334853, 1190304, 1191022, 1236063, 1421546, 1072788, 402981, 996875
        )
    }

}