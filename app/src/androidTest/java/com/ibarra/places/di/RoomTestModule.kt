package com.ibarra.places.di

import androidx.room.Room
import com.ibarra.places.data.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val RoomInstrumentalTestModule = module {
    single {
        // In-Memory database config
        Room.inMemoryDatabaseBuilder(androidContext(), AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
    single { get<AppDatabase>().getRestaurantDao() }
}