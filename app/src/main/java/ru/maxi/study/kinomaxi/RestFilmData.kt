package ru.maxi.study.kinomaxi

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
    @SerializedName("nameRu") val nameRus: String,
    @SerializedName("nameEn") val nameEng: String,
)
