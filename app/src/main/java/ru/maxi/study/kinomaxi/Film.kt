package ru.maxi.study.kinomaxi

/**
 * Данные о фильме
 */
data class Film(
        val id: Long,
        val genres: List<String>,
        val nameRus: String,
        val nameEng: String,
        val slogan: String,
        val year: Int,
        val length: Int,
        val description: String,
        val ageRating: Int,
)