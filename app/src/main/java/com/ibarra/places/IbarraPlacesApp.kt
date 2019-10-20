package com.ibarra.places

import android.app.Application
import com.ibarra.places.di.NetworkModule
import com.ibarra.places.di.RoomModule
import com.ibarra.places.di.ViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IbarraPlacesApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@IbarraPlacesApp)
            modules(listOf(NetworkModule, RoomModule, ViewModule))
        }

    }
}