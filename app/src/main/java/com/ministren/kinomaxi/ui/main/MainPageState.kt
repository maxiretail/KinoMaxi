package com.ministren.kinomaxi.ui.main

import com.ministren.kinomaxi.ui.main.entity.MainPageData

/**
 * Возможные состояния главного экрана
 */
sealed class MainPageState {

    /**
     * Происходит загрузка
     */
    object Loading : MainPageState()

    /**
     * Произошла ошибка
     */
    object Error : MainPageState()

    /**
     * Данные загружены
     *
     * [data] - данные экрана
     */
    data class Loaded(val data: MainPageData) : MainPageState()

    /**
     * Происходит обновление
     */
    object Refreshing : MainPageState()

}