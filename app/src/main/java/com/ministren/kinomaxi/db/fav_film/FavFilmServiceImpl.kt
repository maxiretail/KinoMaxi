package com.ministren.kinomaxi.db.fav_film

import android.content.Context
import androidx.room.Room
import com.ministren.kinomaxi.db.Database
import com.ministren.kinomaxi.entity.FavFilm

class FavFilmServiceImpl(appContext: Context): FavFilmService {
    val favFilmDAO: FavFilmDAO = Room.databaseBuilder(
            appContext,
            Database::class.java,
            "database"
    ).build().favFilmDao()

    override suspend fun save(film: FavFilm) {
        favFilmDAO.save(film.fromEntity())
    }

    override suspend fun getAll(): List<FavFilm> {
        return favFilmDAO.getAll().map { it.toEntity() }
    }
}