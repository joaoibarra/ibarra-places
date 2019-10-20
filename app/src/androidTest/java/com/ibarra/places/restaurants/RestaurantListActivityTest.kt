package com.ibarra.places.restaurants

import androidx.test.espresso.intent.Intents
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.ibarra.places.ui.restaurants.RestaurantListActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest

@LargeTest
@RunWith(JUnit4::class)
class RestaurantListActivityTest: KoinTest {
    @get:Rule
    var activityRule = ActivityTestRule(RestaurantListActivity::class.java, true, false)

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8000)
        Intents.init()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Intents.release()
    }

    @Test
    fun whenOpenScreenShouldDisplay1RestaurantList() {
        arrange(activityRule) {
            mockWebServer(mockWebServer)
            launchActivity()
        }

        assert {
            isRestaurantListVisible()
        }
    }

    @Test
    fun whenOpenScreenShouldDisplayRestaurantItemList() {
        arrange(activityRule) {
            mockWebServer(mockWebServer)
            launchActivity()
        }

        assert {
            verifyItemByPosition(1)
        }
    }
}