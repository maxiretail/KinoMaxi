package com.ministren.kinomaxi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ministren.kinomaxi.db.fav_film.DBFavFilm
import com.ministren.kinomaxi.db.fav_film.FavFilmDAO

/**
 * БД
 */
@Database(entities = arrayOf(DBFavFilm::class), version = 1)
abstract class Database: RoomDatabase() {
    /**
     * DAO для избранных товаров
     */
    abstract fun favFilmDao(): FavFilmDAO
}