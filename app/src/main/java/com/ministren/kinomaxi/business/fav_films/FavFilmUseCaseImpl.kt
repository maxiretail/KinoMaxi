package com.ministren.kinomaxi.business.fav_films

import com.ministren.kinomaxi.db.fav_film.FavFilmService
import com.ministren.kinomaxi.entity.FavFilm
import com.ministren.kinomaxi.entity.Film

class FavFilmUseCaseImpl(private val favFilmService: FavFilmService): FavFilmUseCase {

    override suspend fun save(film: Film) {
        val favFilm = FavFilm(filmId = film.id, posterUrl = film.posterUrl)
        favFilmService.save(favFilm)
    }

    override suspend fun getAll(): List<FavFilm> {
        return favFilmService.getAll()
    }
}