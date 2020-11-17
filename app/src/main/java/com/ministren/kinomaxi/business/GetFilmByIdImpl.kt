package com.ministren.kinomaxi.business

import com.ministren.kinomaxi.entity.Film
import com.ministren.kinomaxi.entity.FilmFrame
import com.ministren.kinomaxi.network.ApiService
import com.ministren.kinomaxi.network.RestFilmDataResponse
import com.ministren.kinomaxi.network.RestFilmFramesResponse

class GetFilmByIdImpl(
    private val apiService: ApiService
) : GetFilmById {

    override suspend fun invoke(filmId: Long): Film {
        val filmDataResponse = apiService.getFilmData(filmId)
        val filmFramesResponse = apiService.getFilmFrames(filmId)
        return getFilmFromRest(filmDataResponse, filmFramesResponse)
    }

    private fun getFilmFromRest(
        filmDataResponse: RestFilmDataResponse,
        filmFramesResponse: RestFilmFramesResponse,
    ): Film {
        return Film(
            id = filmDataResponse.film.id,
            genres = filmDataResponse.film.genres.map { it.name },
            nameRus = filmDataResponse.film.nameRus,
            nameEng = filmDataResponse.film.nameEng,
            slogan = filmDataResponse.film.slogan,
            year = filmDataResponse.film.year,
            length = with(filmDataResponse.film.length ?: "0:0") {
                val (hours, minutes) = split(':').takeLast(2)
                hours.toInt() * 60 + minutes.toInt()
            },
            description = filmDataResponse.film.description,
            ageRating = filmDataResponse.film.ageRating,
            posterUrl = filmDataResponse.film.posterUrlPreview,
            frames = filmFramesResponse.frames.map {
                FilmFrame(it.imageUrl, it.previewUrl)
            }
        )
    }

}