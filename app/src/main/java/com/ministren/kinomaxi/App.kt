package com.ministren.kinomaxi

import android.app.Application
import com.ministren.kinomaxi.di.ViewModelFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ViewModelFactory.instance = ViewModelFactory.Builder().setContext(this).build()
    }

    companion object {
        const val API_BASE_URL = "https://kinopoiskapiunofficial.tech"
        const val API_KEY = "06e6c6a0-6bd0-4d26-9473-d39fa28f75bb"

    }

}