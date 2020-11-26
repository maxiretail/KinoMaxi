package com.ministren.kinomaxi.db.fav_film

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Сервис для работы с избранными фильмами
 */
@Dao
interface FavFilmDAO {
    /**
     * Сохранить избранный фильм
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(favFilm: DBFavFilm)

    /**
     * Получить список избранных фильмов
     */
    @Query("SELECT * FROM fav_film ORDER BY id DESC")
    fun getAll(): Flow<List<DBFavFilm>>
}