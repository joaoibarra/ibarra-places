package com.ibarra.places.data.remote

import com.ibarra.places.data.remote.domain.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IbarraPlacesApi {
    @GET("api/v2.1/search")
    fun getRestaurants(@Query("lat") latitude: String,
                   @Query("lon") longitude: String,
                   @Query("start") start: Int = 0,
                   @Query("count") count: Int = 20): Single<SearchResponse>
}