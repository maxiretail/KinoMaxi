package com.ministren.kinomaxi.ui.film.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.ministren.kinomaxi.R
import com.ministren.kinomaxi.databinding.FragmentFilmDetailsBinding
import com.ministren.kinomaxi.di.ViewModelFactory
import com.ministren.kinomaxi.entity.Film
import com.ministren.kinomaxi.ui.film.frames.FilmFramesAdapter

class FilmDetailsFragment : Fragment() {

    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    private val filmViewModel: FilmViewModel by viewModels() { ViewModelFactory.instance }
    private val filmFramesAdapter = FilmFramesAdapter()

    private var filmId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmId = requireArguments().getLong(ARG_FILM_ID_KEY, 0)
    }

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
            filmViewModel.loadFilmById(filmId, byRefresh = true)
        }

        filmViewModel.state.observe(viewLifecycleOwner, this::showNewState)

        if (filmViewModel.state.value == null) {
            filmViewModel.loadFilmById(filmId)
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

    companion object {

        private const val ARG_FILM_ID_KEY = "ARG_FILM_ID"

        fun getInstance(filmId: Long): FilmDetailsFragment {
            return FilmDetailsFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_FILM_ID_KEY, filmId)
                }
            }
        }

    }

}