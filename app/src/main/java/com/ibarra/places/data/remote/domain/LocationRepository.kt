package com.ibarra.places.data.remote.domain

import com.google.gson.annotations.SerializedName

class LocationRepository (
    @SerializedName("address") val address: String?,
    @SerializedName("locality") val locality: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("latitude") val latitude: Double?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("zipcode") val zipcode: String?,
    @SerializedName("country_id") val countryId: Int?
)