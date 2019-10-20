package com.ibarra.places.di

import com.ibarra.places.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val RoomModule = module {
    single { AppDatabase.getInstance(androidApplication().applicationContext) }
}