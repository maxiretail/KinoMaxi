package com.ministren.kinomaxi.business

import com.ministren.kinomaxi.entity.FilmsTopType
import com.ministren.kinomaxi.entity.TopFilms

/**
 * Бизнес-сценарий загрузки списка топ фильмов
 */
interface GetTopFilms {

    suspend operator fun invoke(type: FilmsTopType, page: Int): TopFilms

}