package com.ministren.kinomaxi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FilmViewModel : ViewModel() {

    private val filmLiveData = MutableLiveData<Film?>()

    val film: LiveData<Film?>
        get() = filmLiveData

    fun loadFilmById(id: Long) {
        viewModelScope.launch {
            val filmDataResponse = App.instance.apiService.getFilmData(id)
            val filmFramesResponse = App.instance.apiService.getFilmFrames(id)
            val film = getFilmFromRest(filmDataResponse, filmFramesResponse)
            filmLiveData.postValue(film)
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
                year = filmDataResponse.film.year.toInt(),
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