package com.ibarra.places.data.remote.domain

import com.google.gson.annotations.SerializedName

class RestaurantResponse (
    @SerializedName("restaurant") val restaurant: RestaurantRepository
)