package com.ministren.kinomaxi.network

import com.google.gson.annotations.SerializedName

/**
 * Ответ на запрос списка кадров фильма
 */
data class RestFilmFramesResponse(
    @SerializedName("frames") val frames: List<RestFilmFrame>,
)

/**
 * REST-представление кадра фильма
 */
data class RestFilmFrame(
    @SerializedName("image") val imageUrl: String,
    @SerializedName("preview") val previewUrl: String,
)