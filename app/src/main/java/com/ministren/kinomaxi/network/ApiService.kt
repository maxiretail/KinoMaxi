package com.ministren.kinomaxi.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

    /**
     * Получение топ списка фильмов с типом  [type] на странице [page]
     */
    @GET("/api/v2.2/films/top")
    suspend fun getTopFilms(
        @Query("type") type: String,
        @Query("page") page: Int,
    ): RestTopFilmsResponse

}