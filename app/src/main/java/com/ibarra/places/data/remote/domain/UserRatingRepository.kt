package com.ibarra.places.data.remote.domain

import com.google.gson.annotations.SerializedName

class UserRatingRepository  (
    @SerializedName("aggregate_rating") val aggregateRating: Double,
    @SerializedName("rating_text") val ratingText: String,
    @SerializedName("rating_color") val ratingColor: String?,
    @SerializedName("votes") val votes: Int
)