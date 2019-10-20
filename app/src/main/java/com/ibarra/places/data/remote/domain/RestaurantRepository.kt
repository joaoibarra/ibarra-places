package com.ibarra.places.data.remote.domain

import com.google.gson.annotations.SerializedName

class RestaurantRepository (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String?,
    @SerializedName("location") val location: LocationRepository?,
    @SerializedName("average_cost_for_two") val averageCostForTwo: Double?,
    @SerializedName("price_range") val priceRange: Int?,
    @SerializedName("currency") val currency: String?,
    @SerializedName("thumb") val thumb: String?,
    @SerializedName("featured_image") val featuredImage: String?,
    @SerializedName("photos_url") val photosUrl: String?,
    @SerializedName("menu_url") val menuUrl: String?,
    @SerializedName("events_url") val eventsUrl: String?,
    @SerializedName("user_rating") val userRating: UserRatingRepository?,
    @SerializedName("all_reviews_count") val allReviewsCount: Int?,
    @SerializedName("phone_numbers") val phoneNumber: String?,
    @SerializedName("cuisines") val cuisines: String?
)