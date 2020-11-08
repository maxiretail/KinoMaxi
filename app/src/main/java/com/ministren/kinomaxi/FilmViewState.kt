package com.ministren.kinomaxi

/**
 * Возможные состояния экрана детальной информации о фильме
 */
sealed class FilmViewState {

    /**
     * Происходит загрузка
     */
    object Loading : FilmViewState()

    /**
     * Произошла ошибка
     */
    object Error : FilmViewState()

    /**
     * Данные загружены
     *
     * [film] - информация о фильме
     */
    data class Loaded(
            val film: Film,
    ) : FilmViewState()

}