package com.ministren.kinomaxi.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ministren.kinomaxi.business.GetTopFilms
import com.ministren.kinomaxi.business.fav_films.FavFilmUseCase
import com.ministren.kinomaxi.entity.FilmsTopType
import com.ministren.kinomaxi.ui.main.entity.MainPageData
import kotlinx.coroutines.launch

class MainPageViewModel(
    private val getTopFilms: GetTopFilms,
    private val favFilmUseCase: FavFilmUseCase
) : ViewModel() {

    private val stateLiveData = MutableLiveData<MainPageState>()
    private var cacheMainPageData: MainPageData? = null

    val state: LiveData<MainPageState>
        get() = stateLiveData

    fun loadData(byRefresh: Boolean = false) {
        viewModelScope.launch {
            if (byRefresh) {
                stateLiveData.postValue(MainPageState.Refreshing)
            } else {
                stateLiveData.postValue(MainPageState.Loading)
            }
            try {
                val favFilms = favFilmUseCase.getAll()
                cacheMainPageData?.let {
                    stateLiveData.postValue(MainPageState.Loaded(MainPageData(
                        favFilms,
                        it.topBestFilms,
                        it.topPopularFilms,
                        it.topAwaitFilms
                    )))
                    return@launch
                }
                val topBestFilms = getTopFilms(FilmsTopType.TOP_250_BEST_FILMS, 1)
                val topPopularFilms = getTopFilms(FilmsTopType.TOP_100_POPULAR_FILMS, 1)
                val topAwaitFilms = getTopFilms(FilmsTopType.TOP_AWAIT_FILMS, 1)
                val mainPageData = MainPageData(
                    favFilms,
                    topBestFilms,
                    topPopularFilms,
                    topAwaitFilms
                )
                cacheMainPageData = mainPageData
                stateLiveData.postValue(MainPageState.Loaded(mainPageData))
            } catch (exception: Exception) {
                Log.d("MAIN_PAGE_LOG", exception.toString())
                stateLiveData.postValue(MainPageState.Error)
            }
        }
    }

}