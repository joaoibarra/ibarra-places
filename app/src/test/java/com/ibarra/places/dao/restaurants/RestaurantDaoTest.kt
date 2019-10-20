package com.ibarra.places.dao.restaurants

import android.content.Context
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import com.ibarra.places.data.db.AppDatabase
import com.ibarra.places.data.db.dao.RestaurantDao
import com.ibarra.places.data.db.entity.Restaurant
import com.ibarra.places.di.RoomTestModule
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1], manifest=Config.NONE)
class RestaurantDaoTest: KoinTest {
    private val appDatabase: AppDatabase by inject()
    private val restaurantDao: RestaurantDao by inject()

    @Before
    fun before() {
        val app: Context = InstrumentationRegistry.getInstrumentation().context
        startKoin {
            androidContext(app)
            modules(listOf(RoomTestModule))
        }
    }

    @After
    fun after() {
        appDatabase.close()
        stopKoin()
    }

    @Test
    fun testInsertRestaurant() {
        val restaurant = Restaurant.to(restaurantRepository)

        restaurantDao.insert(restaurant)

        val requestRestaurant = restaurantDao.findById(restaurantRepository.id)

        Assert.assertEquals(restaurant, requestRestaurant)
    }
}