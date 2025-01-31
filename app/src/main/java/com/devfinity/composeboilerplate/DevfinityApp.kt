package com.devfinity.composeboilerplate

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DevfinityApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("App","app")
    }
}