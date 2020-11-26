package com.ministren.kinomaxi.ui.main.entity

/**
 * Элемент списка фильмов на главной
 */
data class FilmItemViewData(
    /**
     * Идентификатор фильма
     */
    val id: Long,
    /**
     * Ссылка на постер фильма
     */
    val posterUrl: String?
)