package com.ministren.kinomaxi.business.fav_films

import com.ministren.kinomaxi.entity.FavFilm
import com.ministren.kinomaxi.entity.Film
import kotlinx.coroutines.flow.Flow

/**
 * Бизнес-логика для работы с избранными фильмами
 */
interface FavFilmUseCase {
    /**
     * Добавление фильма [film] в избранное
     */
    suspend fun save(film: Film)

    /**
     * Получить список фильмов в избранном
     */
    fun getAll(): Flow<List<FavFilm>>
}