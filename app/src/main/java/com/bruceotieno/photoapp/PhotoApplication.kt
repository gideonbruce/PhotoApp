package com.bruceotieno.photoapp

import android.app.Application
import com.bruceotieno.photoapp.data.AppContainer
import com.bruceotieno.photoapp.data.DefaultAppContainer

class PhotoApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}