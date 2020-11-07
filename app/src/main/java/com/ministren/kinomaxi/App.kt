package com.ministren.kinomaxi

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("X-API-KEY", API_KEY)
                .build()
            it.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private const val API_BASE_URL = "https://kinopoiskapiunofficial.tech"
        private const val API_KEY = "06e6c6a0-6bd0-4d26-9473-d39fa28f75bb"

        lateinit var instance: App
            private set
    }

}