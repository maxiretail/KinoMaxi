package ru.maxi.study.kinomaxi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Интерфейс взаимодействия с REST API
 */
interface KinopoiskApiService {

    /**
     * Получение данных о фильме по идентификатору [filmId]
     */
    @GET("/api/v2.1/films/{id}")
    fun getFilmData(@Path("id") filmId: Int): Call<RestFilmDataResponse>
}
