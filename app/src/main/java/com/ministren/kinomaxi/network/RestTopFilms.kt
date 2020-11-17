package com.ministren.kinomaxi.network

import com.google.gson.annotations.SerializedName

/**
 * Ответ на запрос топ списка фильмов
 */
data class RestTopFilmsResponse(
    @SerializedName("pagesCount") val pagesCount: Int,
    @SerializedName("films") val films: List<RestFilm>
)