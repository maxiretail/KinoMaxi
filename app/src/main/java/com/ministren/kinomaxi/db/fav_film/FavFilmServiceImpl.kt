package com.ministren.kinomaxi.db.fav_film

import com.ministren.kinomaxi.db.Database
import com.ministren.kinomaxi.entity.FavFilm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavFilmServiceImpl(database: Database) : FavFilmService {

    private val favFilmDAO: FavFilmDAO = database.favFilmDao()

    override suspend fun save(film: FavFilm) {
        favFilmDAO.save(film.fromEntity())
    }

    override fun getAll(): Flow<List<FavFilm>> {
        return favFilmDAO.getAll().map { films -> films.map { it.toEntity() } }
    }
}