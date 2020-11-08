package com.ministren.kinomaxi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FilmViewModel : ViewModel() {

    private val stateLiveData = MutableLiveData<FilmViewState>()

    val state: LiveData<FilmViewState>
        get() = stateLiveData

    fun loadFilmById(id: Long) {
        viewModelScope.launch {
            stateLiveData.postValue(FilmViewState.Loading)
            try {
                val filmDataResponse = App.instance.apiService.getFilmData(id)
                val filmFramesResponse = App.instance.apiService.getFilmFrames(id)
                val film = getFilmFromRest(filmDataResponse, filmFramesResponse)
                stateLiveData.postValue(FilmViewState.Loaded(film))
            } catch (exception: Exception) {
                Log.d("FILM_LOG", exception.toString())
                stateLiveData.postValue(FilmViewState.Error)
            }
        }
    }

    private fun getFilmFromRest(
            filmDataResponse: RestFilmDataResponse,
            filmFramesResponse: RestFilmFramesResponse,
    ): Film {
        return Film(
                id = filmDataResponse.film.id,
                genres = filmDataResponse.film.genres.map { it.name },
                nameRus = filmDataResponse.film.nameRus,
                nameEng = filmDataResponse.film.nameEng,
                slogan = filmDataResponse.film.slogan,
                year = filmDataResponse.film.year,
                length = with(filmDataResponse.film.length) {
                    val (hours, minutes) = split(':').takeLast(2)
                    hours.toInt() * 60 + minutes.toInt()
                },
                description = filmDataResponse.film.description,
                ageRating = filmDataResponse.film.ageRating,
                posterUrl = filmDataResponse.film.posterUrlPreview,
                frames = filmFramesResponse.frames.map {
                    FilmFrame(it.imageUrl, it.previewUrl)
                }
        )
    }

}