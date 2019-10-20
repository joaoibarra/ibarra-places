package com.ibarra.places.di

import com.ibarra.places.ui.restaurants.RestaurantListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModule = module {
    viewModel { RestaurantListViewModel(get(), get()) }
}