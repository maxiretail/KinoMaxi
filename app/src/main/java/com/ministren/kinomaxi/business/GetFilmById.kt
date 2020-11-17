package com.ministren.kinomaxi.business

import com.ministren.kinomaxi.model.Film

/**
 * Бизнес-сценарий загрузки детальной информации о фильме по идентификатору
 */
interface GetFilmById {

    suspend operator fun invoke(filmId: Long): Film

}