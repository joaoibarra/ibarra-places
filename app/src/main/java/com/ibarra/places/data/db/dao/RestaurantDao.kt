package com.ibarra.places.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.ibarra.places.data.db.entity.Restaurant
@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, Restaurant>

    @Query("SELECT * FROM restaurants WHERE id LIKE :placeId ORDER BY created ASC")
    fun findByRestaurantId(placeId: String): DataSource.Factory<Int, Restaurant>

    @Query("SELECT * FROM restaurants WHERE id LIKE :placeId ORDER BY created ASC")
    fun findById(placeId: String): Restaurant

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Restaurant)

    @Delete
    fun delete(article: Restaurant)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(sources: List<Restaurant>?)
}