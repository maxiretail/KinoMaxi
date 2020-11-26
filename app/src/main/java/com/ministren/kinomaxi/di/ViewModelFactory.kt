package com.ministren.kinomaxi.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.ministren.kinomaxi.App
import com.ministren.kinomaxi.business.GetFilmById
import com.ministren.kinomaxi.business.GetFilmByIdImpl
import com.ministren.kinomaxi.business.GetTopFilmsImpl
import com.ministren.kinomaxi.business.fav_films.FavFilmUseCase
import com.ministren.kinomaxi.business.fav_films.FavFilmUseCaseImpl
import com.ministren.kinomaxi.db.Database
import com.ministren.kinomaxi.db.fav_film.FavFilmService
import com.ministren.kinomaxi.db.fav_film.FavFilmServiceImpl
import com.ministren.kinomaxi.network.ApiService
import com.ministren.kinomaxi.ui.film.details.FilmViewModel
import com.ministren.kinomaxi.ui.main.MainPageViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Фабрика для создания ViewModel'ей
 */
class ViewModelFactory private constructor(
    appContext: Context,
) : ViewModelProvider.Factory {

    private val database: Database = Room.databaseBuilder(
        appContext,
        Database::class.java,
        "database"
    ).build()

    private val favFilmService: FavFilmService = FavFilmServiceImpl(database)
    private val favFilmUseCase: FavFilmUseCase = FavFilmUseCaseImpl(favFilmService)
    private val getFilmByIdUseCase: GetFilmById = GetFilmByIdImpl(apiService)
    private val getTopFilmsImplUseCase: GetTopFilmsImpl = GetTopFilmsImpl(apiService)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmViewModel::class.java)) {
            return FilmViewModel(getFilmByIdUseCase, favFilmUseCase) as T
        }
        if (modelClass.isAssignableFrom(MainPageViewModel::class.java)) {
            return MainPageViewModel(getTopFilmsImplUseCase, favFilmUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    class Builder {

        private var context: Context? = null

        fun setContext(context: Context): Builder = apply {
            this.context = context
        }

        fun build(): ViewModelFactory {
            val context = this.context
                ?: throw IllegalStateException("Application context should be provided via setContext method")
            return ViewModelFactory(context)
        }

    }

    companion object {

        lateinit var instance: ViewModelFactory

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

        private val apiService: ApiService = retrofit.create(ApiService::class.java)

    }

}