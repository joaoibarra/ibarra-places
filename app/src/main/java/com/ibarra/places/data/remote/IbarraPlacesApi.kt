package com.ibarra.places.data.remote

import com.ibarra.places.data.remote.domain.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IbarraPlacesApi {
    @GET("api/v2.1/search")
    fun getSources(@Query("lat") latitude: String,
                   @Query("lon") longitude: String,
                   @Query("lon") start: Int = 0,
                   @Query("lon") count: Int = 20): Single<SearchResponse>
}