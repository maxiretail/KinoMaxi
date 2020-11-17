package com.ministren.kinomaxi.entity

/**
 * Сущность списка топ фильмов
 */
data class TopFilms(
    val type: FilmsTopType,
    val pagesCount: Int,
    val films: List<Film>,
)