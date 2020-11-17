package com.ministren.kinomaxi.network

import com.google.gson.annotations.SerializedName

/**
 * Ответ на запрос данных о фильме
 */
data class RestFilmDataResponse(
    @SerializedName("data") val film: RestFilm,
)