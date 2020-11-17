package com.ministren.kinomaxi.ui.film.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ministren.kinomaxi.business.GetFilmById
import kotlinx.coroutines.launch

class FilmViewModel(
    private val getFilmById: GetFilmById,
) : ViewModel() {

    private val stateLiveData = MutableLiveData<FilmViewState>()

    val state: LiveData<FilmViewState>
        get() = stateLiveData

    fun loadFilmById(id: Long) {
        viewModelScope.launch {
            stateLiveData.postValue(FilmViewState.Loading)
            try {
                val film = getFilmById(id)
                stateLiveData.postValue(FilmViewState.Loaded(film))
            } catch (exception: Exception) {
                Log.d("FILM_LOG", exception.toString())
                stateLiveData.postValue(FilmViewState.Error)
            }
        }
    }

}