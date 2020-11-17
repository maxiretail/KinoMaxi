package com.ministren.kinomaxi.entity

/**
 * Сущность фильма
 */
data class Film(
    val id: Long,
    val genres: List<String>,
    val nameRus: String,
    val nameEng: String?,
    val slogan: String? = null,
    val year: String,
    val length: Int,
    val description: String? = null,
    val ageRating: Int? = null,
    val posterUrl: String,
    val frames: List<FilmFrame> = emptyList(),
)