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
        filmViewModel.state.observe(this) { state ->
            when (state) {
                FilmViewState.Loading -> {
                    binding.loaderView.visibility = View.VISIBLE
                    binding.contentView.visibility = View.GONE
                    binding.errorView.visibility = View.GONE
                }
                FilmViewState.Error -> {
                    binding.loaderView.visibility = View.GONE
                    binding.contentView.visibility = View.GONE
                    binding.errorView.visibility = View.VISIBLE
                }
                is FilmViewState.Loaded -> {
                    binding.loaderView.visibility = View.GONE
                    binding.contentView.visibility = View.VISIBLE
                    binding.errorView.visibility = View.GONE
                    showFilmInfo(state.film)
                }
            }
        }
        filmViewModel.loadFilmById(263531)
    }

    private fun showFilmInfo(film: Film) {
        with(binding.filmDetailsLayout) {
            filmGenres.text = film.genres.joinToString(separator = ", ")
            filmNameRus.text = film.nameRus
            filmNameEng.text = film.nameEng
            filmSlogan.text = film.slogan
            filmYear.text = film.year.toString()
            filmLength.text = getString(R.string.film_length_value, film.length)
        }

        binding.filmDescription.text = film.description

        with(binding.filmPosterLayout) {
            filmAgeRating.text = getString(R.string.film_age_rating, film.ageRating)
            Glide.with(this@MainActivity).load(film.posterUrl).into(filmPoster)
        }

        filmFramesAdapter.setItems(film.frames)
    }
}