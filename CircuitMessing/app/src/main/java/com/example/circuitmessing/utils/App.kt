package com.escaper.escaper.utils

import android.app.Application

val preferences: Preferences by lazy {
    App.preferences!!
}

class App : Application() {
    companion object {
        var preferences: Preferences? = null
    }

    override fun onCreate() {
        preferences = Preferences(applicationContext)
        super.onCreate()
    }
}