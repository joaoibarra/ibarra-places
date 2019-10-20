package com.ibarra.places.ui.restaurants

import androidx.paging.PagedList
import com.ibarra.places.data.db.entity.Restaurant

class RestaurantListBoundaryCallback (
    private val viewModel: RestaurantListViewModel
) : PagedList.BoundaryCallback<Restaurant>() {
    override fun onZeroItemsLoaded() {
    }

    override fun onItemAtEndLoaded(itemAtEnd: Restaurant) {
        viewModel.getNearbyLocation()
    }
}