package com.ministren.kinomaxi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val film = Film(
                id = 263531,
                genres = listOf("боевик", "фантастика", "фэнтези"),
                nameRus = "Мстители",
                nameEng = "The Avengers",
                slogan = "Avengers Assemble!",
                year = 2012,
                length = 137,
                description = "Локи, сводный брат Тора, возвращается, и в этот раз он не один. Земля оказывается на грани порабощения, и только лучшие из лучших могут спасти человечество. Глава международной организации Щ.И.Т. Ник Фьюри собирает выдающихся поборников справедливости и добра, чтобы отразить атаку. Под предводительством Капитана Америки Железный Человек, Тор, Невероятный Халк, Соколиный Глаз и Чёрная Вдова вступают в войну с захватчиком.",
                ageRating = 12
        )
        showFilmInfo(film)
    }

    private fun showFilmInfo(film: Film) {
        val filmGenresTextView = findViewById<TextView>(R.id.film_genres)
        val filmNameRusTextView = findViewById<TextView>(R.id.film_name_rus)
        val filmNameEngTextView = findViewById<TextView>(R.id.film_name_eng)
        val filmSloganTextView = findViewById<TextView>(R.id.film_slogan)
        val filmYearTextView = findViewById<TextView>(R.id.film_year)
        val filmLengthTextView = findViewById<TextView>(R.id.film_length)
        val filmDescriptionTextView = findViewById<TextView>(R.id.film_description)
        val filmAgeRatingTextView = findViewById<TextView>(R.id.film_age_rating)

        filmGenresTextView.text = film.genres.joinToString(separator = ", ")
        filmNameRusTextView.text = film.nameRus
        filmNameEngTextView.text = film.nameEng
        filmSloganTextView.text = film.slogan
        filmYearTextView.text = film.year.toString()
        filmLengthTextView.text = getString(R.string.film_length_value, film.length)
        filmDescriptionTextView.text = film.description
        filmAgeRatingTextView.text = getString(R.string.film_age_rating, film.ageRating)
    }
}