package com.ibarra.places.ui.restaurants

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.ibarra.places.data.db.entity.Restaurant

@BindingAdapter(value = ["restaurants", "viewModel"])
fun setRestaurants(view: RecyclerView, items: PagedList<Restaurant>?, vm: RestaurantListViewModel) {
    view.adapter?.run {
        if (this is RestaurantAdapter) this.submitList(items)
    } ?: run {
        RestaurantAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}