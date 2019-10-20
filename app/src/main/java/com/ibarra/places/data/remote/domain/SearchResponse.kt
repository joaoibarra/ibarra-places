package com.ibarra.places.data.remote.domain

import com.google.gson.annotations.SerializedName

class SearchResponse (
    @SerializedName("results_found") val resultsFound: Int,
    @SerializedName("results_start") val resultsStart: Int,
    @SerializedName("results_shown") val resultsShown: Int,
    @SerializedName("restaurants") val restaurants: List<RestaurantResponse>
)