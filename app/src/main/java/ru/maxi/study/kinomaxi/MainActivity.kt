package ru.maxi.study.kinomaxi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ru.maxi.study.kinomaxi.databinding.ActivityMainBinding

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