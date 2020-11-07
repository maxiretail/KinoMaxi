package com.ministren.kinomaxi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ministren.kinomaxi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val film = Film(
                id = 263531,
                genres = listOf("боевик", "фантастика", "фэнтези"),
                nameRus = "Мстители",
                nameEng = "The Avengers",
                slogan = "Avengers Assemble!",
                year = 2012,
                length = 137,
                description = "Локи, сводный брат Тора, возвращается, и в этот раз он не один. Земля оказывается на грани порабощения, и только лучшие из лучших могут спасти человечество. Глава международной организации Щ.И.Т. Ник Фьюри собирает выдающихся поборников справедливости и добра, чтобы отразить атаку. Под предводительством Капитана Америки Железный Человек, Тор, Невероятный Халк, Соколиный Глаз и Чёрная Вдова вступают в войну с захватчиком.",
                ageRating = 12,
                posterUrl = "https://kinopoiskapiunofficial.tech/images/posters/kp_small/263531.jpg",
                frameUrls = listOf(
                        "https://kinopoiskapiunofficial.tech/images/frames/kp/kadr/sm_1957128.jpg",
                        "https://kinopoiskapiunofficial.tech/images/frames/kp/kadr/sm_1941720.jpg",
                        "https://kinopoiskapiunofficial.tech/images/frames/kp/kadr/sm_1941719.jpg",
                        "https://kinopoiskapiunofficial.tech/images/frames/kp/kadr/sm_1937806.jpg",
                        "https://kinopoiskapiunofficial.tech/images/frames/kp/kadr/sm_1937805.jpg",
                )
        )
        showFilmInfo(film)
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

        with(Glide.with(this)) {
            load(film.frameUrls[0]).into(binding.filmFrame1)
            load(film.frameUrls[1]).into(binding.filmFrame2)
            load(film.frameUrls[2]).into(binding.filmFrame3)
            load(film.frameUrls[3]).into(binding.filmFrame4)
            load(film.frameUrls[4]).into(binding.filmFrame5)
        }
    }
}