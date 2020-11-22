package com.ministren.kinomaxi.ui.film.details

import androidx.annotation.StringRes

/**
 * View-state для отображения тоста
 */
class ToastViewState(@StringRes private val messageRes: Int) {
    val messageResource: Int
    get() {
        showed = true
        return messageRes
    }
    var showed: Boolean = false
    private set
}