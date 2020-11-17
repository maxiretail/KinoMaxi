package com.ministren.kinomaxi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ministren.kinomaxi.App
import com.ministren.kinomaxi.business.GetFilmByIdImpl
import com.ministren.kinomaxi.business.GetTopFilmsImpl
import com.ministren.kinomaxi.network.ApiService
import com.ministren.kinomaxi.ui.film.details.FilmViewModel
import com.ministren.kinomaxi.ui.main.MainPageViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Фабрика для создания ViewModel'ей
 */
class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmViewModel::class.java)) {
            return FilmViewModel(GetFilmByIdImpl(apiService)) as T
        }
        if (modelClass.isAssignableFrom(MainPageViewModel::class.java)) {
            return MainPageViewModel(GetTopFilmsImpl(apiService)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    private companion object {
        private val httpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("X-API-KEY", App.API_KEY)
                    .build()
                it.proceed(request)
            }
            .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(App.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService: ApiService = retrofit.create(ApiService::class.java)

    }

}