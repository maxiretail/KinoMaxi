package com.ministren.kinomaxi.business

import com.ministren.kinomaxi.entity.Film
import com.ministren.kinomaxi.entity.FilmsTopType
import com.ministren.kinomaxi.entity.TopFilms
import com.ministren.kinomaxi.network.ApiService
import com.ministren.kinomaxi.network.RestTopFilmsResponse

class GetTopFilmsImpl(
    private val apiService: ApiService,
) : GetTopFilms {

    override suspend fun invoke(type: FilmsTopType, page: Int): TopFilms {
        val topFilms = apiService.getTopFilms(type.name, page)
        return getFilmsFromRest(type, topFilms)
    }

    private fun getFilmsFromRest(
        type: FilmsTopType,
        topFilmsResponse: RestTopFilmsResponse,
    ): TopFilms {
        return TopFilms(
            type = type,
            pagesCount = topFilmsResponse.pagesCount,
            films = topFilmsResponse.films.map {
                Film(
                    id = it.id,
                    genres = it.genres.map { genre -> genre.name },
                    nameRus = it.nameRus,
                    nameEng = it.nameEng,
                    year = it.year,
                    length = with(it.length ?: "0:0") {
                        val (hours, minutes) = split(':').takeLast(2)
                        hours.toInt() * 60 + minutes.toInt()
                    },
                    posterUrl = it.posterUrlPreview,
                )
            }
        )
    }

}