package com.ministren.kinomaxi.model

/**
 * Сущность фильма
 */
data class Film(
    val id: Long,
    val genres: List<String>,
    val nameRus: String,
    val nameEng: String,
    val slogan: String?,
    val year: String,
    val length: Int,
    val description: String,
    val ageRating: Int,
    val posterUrl: String,
    val frames: List<FilmFrame>,
)