package com.space307.navtest

import android.app.Application
import com.space307.navtest.utils.PreferenceProvider

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        PreferenceProvider.init(this)
    }
}