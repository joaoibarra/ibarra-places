package com.ibarra.places.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ibarra.places.data.db.converter.DateConverter
import java.util.*

@Entity(tableName = "restaurants")
@TypeConverters(DateConverter::class)
class Restaurant (
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
    @ColumnInfo(name = "created") val created: Date
)