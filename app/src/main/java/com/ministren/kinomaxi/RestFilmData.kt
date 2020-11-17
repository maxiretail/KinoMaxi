package com.ministren.kinomaxi

import com.google.gson.annotations.SerializedName

/**
 * Ответ на запрос данных о фильме
 */
data class RestFilmDataResponse(
    @SerializedName("data") val film: RestFilm,
)

/**
 * REST-представление данных о фильме
 */
data class RestFilm(
    @SerializedName("filmId") val id: Long,
    @SerializedName("nameRu") val nameRus: String,
    @SerializedName("nameEn") val nameEng: String,
    @SerializedName("posterUrlPreview") val posterUrlPreview: String,
    @SerializedName("year") val year: String,
    @SerializedName("filmLength") val length: String,
    @SerializedName("slogan") val slogan: String?,
    @SerializedName("description") val description: String,
    @SerializedName("ratingAgeLimits") val ageRating: Int,
    @SerializedName("genres") val genres: List<RestFilmGenre>,
)

/**
 * REST-представление жанра фильма
 */
data class RestFilmGenre(
    @SerializedName("genre") val name: String,
)