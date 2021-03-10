package ru.maxi.study.kinomaxi

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

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
            ageRating = 12,
            posterUrl = "https://avatars.mds.yandex.net/get-kinopoisk-image/1600647/afab999b-c6bb-4fac-a951-03f72fd2b8cf/600x900",
            frameUrls = listOf(
                "https://avatars.mds.yandex.net/get-kinopoisk-image/1898899/fc13a70c-abac-440d-b88a-eb5343156030/960x960",
                "https://avatars.mds.yandex.net/get-kinopoisk-image/1777765/48e7ffab-3544-461e-a396-998ad18c8dac/960x960",
                "https://avatars.mds.yandex.net/get-kinopoisk-image/1777765/c408a4ef-19e0-4ec0-8337-f1d8c40fafb9/960x960",
                "https://avatars.mds.yandex.net/get-kinopoisk-image/1946459/b7c88a5e-ad0a-4869-979a-4865d6d4a9c8/960x960",
                "https://avatars.mds.yandex.net/get-kinopoisk-image/1773646/ff64737a-fcfa-4b5c-88ba-4e6259069282/960x960",
            )
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

        val filmPosterImageView = findViewById<ImageView>(R.id.film_poster)
        Glide.with(this).load(film.posterUrl).into(filmPosterImageView)

        val filmFrame1ImageView = findViewById<ImageView>(R.id.film_frame_1)
        val filmFrame2ImageView = findViewById<ImageView>(R.id.film_frame_2)
        val filmFrame3ImageView = findViewById<ImageView>(R.id.film_frame_3)
        val filmFrame4ImageView = findViewById<ImageView>(R.id.film_frame_4)
        val filmFrame5ImageView = findViewById<ImageView>(R.id.film_frame_5)
        Glide.with(this).load(film.frameUrls[0]).into(filmFrame1ImageView)
        Glide.with(this).load(film.frameUrls[1]).into(filmFrame2ImageView)
        Glide.with(this).load(film.frameUrls[2]).into(filmFrame3ImageView)
        Glide.with(this).load(film.frameUrls[3]).into(filmFrame4ImageView)
        Glide.with(this).load(film.frameUrls[4]).into(filmFrame5ImageView)
    }

}