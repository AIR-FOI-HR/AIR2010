package com.example.circuitmessing.utils

import android.app.Application
import com.escaper.escaper.utils.Preferences

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