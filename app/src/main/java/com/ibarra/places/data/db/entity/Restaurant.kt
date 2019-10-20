package com.ibarra.places.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ibarra.places.data.db.converter.DateConverter
import com.ibarra.places.data.remote.domain.RestaurantRepository
import com.ibarra.places.data.remote.domain.RestaurantResponse
import java.util.*

@Entity(tableName = "restaurants")
@TypeConverters(DateConverter::class)
data class Restaurant (
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "locality") val locality: String?,
    @ColumnInfo(name = "thumb") val thumb: String?,
    @ColumnInfo(name = "featured_image") val featuredImage: String?,
    @ColumnInfo(name = "phone_numbers") val phoneNumber: String?,
    @ColumnInfo(name = "aggregate_rating") val rating: Double?,
    @ColumnInfo(name = "all_reviews_count") val reviews: Int?,
    @ColumnInfo(name = "cuisines") val cuisines: String?,
    @ColumnInfo(name = "created") val created: Date
) {
    companion object {
        fun to(restaurantRepository: RestaurantRepository): Restaurant {
            return Restaurant (
                id = restaurantRepository.id,
                name = restaurantRepository.name,
                url = restaurantRepository.url,
                latitude = restaurantRepository.location?.latitude,
                longitude = restaurantRepository.location?.longitude,
                address = restaurantRepository.location?.address,
                locality = restaurantRepository.location?.locality,
                thumb = restaurantRepository.thumb,
                featuredImage = restaurantRepository.featuredImage,
                phoneNumber = restaurantRepository.phoneNumber,
                rating = restaurantRepository.userRating?.aggregateRating,
                reviews = restaurantRepository.allReviewsCount,
                cuisines = restaurantRepository.cuisines,
                created = Date()
            )
        }

        fun toList(restaurantRepositories: List<RestaurantResponse>?): List<Restaurant>? {
            return restaurantRepositories?.map { to(it.restaurant) }
        }
    }
}