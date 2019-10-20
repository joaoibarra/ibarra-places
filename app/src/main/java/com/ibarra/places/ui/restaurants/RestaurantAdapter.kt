package com.ibarra.places.ui.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ibarra.places.R
import com.ibarra.places.data.db.entity.Restaurant
import com.ibarra.places.databinding.ItemRestaurantBinding
import com.ibarra.places.ui.binding.BindingViewHolder

class RestaurantAdapter (private val vm: RestaurantListViewModel) :
    PagedListAdapter<Restaurant, RestaurantAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_restaurant,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { restaurant ->
            holder.binding?.apply{
                 item = restaurant
                viewModel = vm
                loadImage(ivRestaurantImage, restaurant.featuredImage)
            }
        }
    }

    private fun loadImage(view: ImageView, url: String?){
        Glide.with(view.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }

    class CategoryViewHolder(view: View) : BindingViewHolder<ItemRestaurantBinding>(view)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem == newItem
        }
    }
}