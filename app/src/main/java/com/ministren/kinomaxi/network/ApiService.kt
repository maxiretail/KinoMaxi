package com.ministren.kinomaxi.network

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Интерфейс взаимодействия с REST API
 */
interface ApiService {

    /**
     * Получение данных о фильме по идентификатору [filmId]
     */
    @GET("/api/v2.1/films/{id}")
    suspend fun getFilmData(@Path("id") filmId: Long): RestFilmDataResponse

    /**
     * Получение списка кадров фильма по идентификатору [filmId]
     */
    @GET("/api/v2.1/films/{id}/frames")
    suspend fun getFilmFrames(@Path("id") filmId: Long): RestFilmFramesResponse

}