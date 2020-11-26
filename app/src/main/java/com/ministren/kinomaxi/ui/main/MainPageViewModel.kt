package com.ministren.kinomaxi.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.ministren.kinomaxi.business.GetTopFilms
import com.ministren.kinomaxi.business.fav_films.FavFilmUseCase
import com.ministren.kinomaxi.entity.FavFilm
import com.ministren.kinomaxi.entity.FilmsTopType
import com.ministren.kinomaxi.ui.main.entity.MainPageData
import kotlinx.coroutines.launch

class MainPageViewModel(
    private val getTopFilms: GetTopFilms,
    private val favFilmUseCase: FavFilmUseCase,
) : ViewModel() {

    private val stateLiveData = MutableLiveData<MainPageState>()

    val state: LiveData<MainPageState>
        get() = stateLiveData

    val favorites: LiveData<List<FavFilm>>
        get() = favFilmUseCase.getAll().asLiveData()

    fun loadData(byRefresh: Boolean = false) {
        viewModelScope.launch {
            if (byRefresh) {
                stateLiveData.postValue(MainPageState.Refreshing)
            } else {
                stateLiveData.postValue(MainPageState.Loading)
            }
            try {
                val topBestFilms = getTopFilms(FilmsTopType.TOP_250_BEST_FILMS, 1)
                val topPopularFilms = getTopFilms(FilmsTopType.TOP_100_POPULAR_FILMS, 1)
                val topAwaitFilms = getTopFilms(FilmsTopType.TOP_AWAIT_FILMS, 1)
                val mainPageData = MainPageData(
                    topBestFilms,
                    topPopularFilms,
                    topAwaitFilms
                )
                stateLiveData.postValue(MainPageState.Loaded(mainPageData))
            } catch (exception: Exception) {
                Log.d("MAIN_PAGE_LOG", exception.toString())
                stateLiveData.postValue(MainPageState.Error)
            }
        }
    }

}