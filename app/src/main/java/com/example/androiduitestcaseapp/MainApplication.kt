package com.example.androiduitestcaseapp

import android.app.Application
import android.content.Context

/**
 * This class is the MainApplication class
 * where we can declare any method or variable which will have life throughout application lifecycle
 */
class MainApplication : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}