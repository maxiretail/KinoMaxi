package com.ministren.kinomaxi.ui.main.entity

import com.ministren.kinomaxi.entity.FavFilm
import com.ministren.kinomaxi.entity.TopFilms

/**
 * View-сущность данных главной страницы
 */
data class MainPageData(
    val favFilms: List<FavFilm>,
    val topBestFilms: TopFilms,
    val topPopularFilms: TopFilms,
    val topAwaitFilms: TopFilms,
)