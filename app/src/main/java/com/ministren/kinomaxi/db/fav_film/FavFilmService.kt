package com.ministren.kinomaxi.db.fav_film

import com.ministren.kinomaxi.entity.FavFilm
import kotlinx.coroutines.flow.Flow

/**
 * Сервис для работы с избранными
 * фильмами
 */
interface FavFilmService {
    /**
     * Сохранить
     */
    suspend fun save(film: FavFilm)

    /**
     * Получить список избранных фильмов
     */
    fun getAll(): Flow<List<FavFilm>>
}