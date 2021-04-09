package ru.maxi.study.kinomaxi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.maxi.study.kinomaxi.databinding.FragmentFilmDetailsBinding

class FilmDetailsFragment : Fragment() {

    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    private val filmFramesAdapter = FilmFramesAdapter()

    private val BASE_URL = "https://kinopoiskapiunofficial.tech"
    private val API_KEY = "06e6c6a0-6bd0-4d26-9473-d39fa28f75bb"

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-API-KEY", API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(KinopoiskApiService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
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

        val filmDataCall = apiService.getFilmData(263531)
        filmDataCall.enqueue(object : Callback<RestFilmDataResponse> {
            override fun onResponse(
                call: Call<RestFilmDataResponse>,
                response: Response<RestFilmDataResponse>,
            ) {
                val filmData = response.body()?.film ?: return
                binding.filmDetailsLayout.filmNameRus.text = filmData.nameRus
                binding.filmDetailsLayout.filmNameEng.text = filmData.nameEng
            }

            override fun onFailure(call: Call<RestFilmDataResponse>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
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
            Glide.with(this@FilmDetailsFragment).load(film.posterUrl).into(filmPoster)
        }

        filmFramesAdapter.setItems(film.frameUrls)
    }

}