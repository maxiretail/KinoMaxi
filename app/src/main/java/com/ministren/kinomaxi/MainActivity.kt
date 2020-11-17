package com.ministren.kinomaxi

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ministren.kinomaxi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val filmFramesAdapter = FilmFramesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.filmFramesView.adapter = filmFramesAdapter

        val filmViewModel = FilmViewModel()
        binding.root.setOnRefreshListener {
            filmViewModel.loadFilmById(FILM_IDS.random())
        }
        filmViewModel.state.observe(this) { state ->
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
        filmViewModel.loadFilmById(FILM_IDS.random())
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
            Glide.with(this@MainActivity).load(film.posterUrl).into(filmPoster)
        }

        filmFramesAdapter.setItems(film.frames)
    }

    private companion object {
        val FILM_IDS = listOf<Long>(
            1253633, 279091, 1334853, 1190304, 1191022, 1236063, 1421546, 1072788, 402981, 996875
        )
    }
}