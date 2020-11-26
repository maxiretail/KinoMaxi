package com.ministren.kinomaxi.ui.film.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ministren.kinomaxi.R
import com.ministren.kinomaxi.business.GetFilmById
import com.ministren.kinomaxi.business.fav_films.FavFilmUseCase
import kotlinx.coroutines.launch

class FilmViewModel(
    private val getFilmById: GetFilmById,
    private val favFilmUseCase: FavFilmUseCase
) : ViewModel() {

    private val stateLiveData = MutableLiveData<FilmViewState>()
    private val toastLiveData = MutableLiveData<ToastViewState>()

    val state: LiveData<FilmViewState>
        get() = stateLiveData
    val toast: LiveData<ToastViewState>
        get() = toastLiveData


    fun loadFilmById(id: Long, byRefresh: Boolean = false) {
        viewModelScope.launch {
            if (byRefresh) {
                stateLiveData.postValue(FilmViewState.Refreshing)
            } else {
                stateLiveData.postValue(FilmViewState.Loading)
            }
            try {
                val film = getFilmById(id)
                stateLiveData.postValue(FilmViewState.Loaded(film))
            } catch (exception: Exception) {
                Log.d("FILM_LOG", exception.toString())
                stateLiveData.postValue(FilmViewState.Error)
            }
        }
    }

    fun addFavorite() {
        viewModelScope.launch {
            (state.value as? FilmViewState.Loaded)?.let {
                favFilmUseCase.save(film = it.film)
                toastLiveData.postValue(ToastViewState(R.string.fav_film_add))
            }
        }
    }
}