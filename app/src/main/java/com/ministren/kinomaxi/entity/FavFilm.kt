package com.ministren.kinomaxi.entity

/**
 * Сущность избранного фильма
 */
data class FavFilm(
    /**
     * Идентификатор
     */
    val id: Long? = null,
    /**
     * Идентификатор фильма
     */
    val filmId: Long,
    /**
     * Постер
     */
    val posterUrl: String
)