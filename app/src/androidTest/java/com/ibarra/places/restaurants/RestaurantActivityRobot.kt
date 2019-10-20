package com.ibarra.places.restaurants

import android.Manifest
import android.content.Intent
import androidx.test.rule.ActivityTestRule
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewInteractions
import br.com.concretesolutions.kappuccino.custom.runtimePermission.runtimePermission
import com.ibarra.places.R
import com.ibarra.places.extensions.loadResponse
import com.ibarra.places.ui.restaurants.RestaurantListActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

fun arrange(rule: ActivityTestRule<RestaurantListActivity>, func: RestaurantListActivityArrangeRobot.() -> Unit) = RestaurantListActivityArrangeRobot(
    rule
).apply { func() }
fun act(func: RestaurantListActivityActRobot.() -> Unit) = RestaurantListActivityActRobot().apply { func() }
fun assert(func: RestaurantListActivityAssertRobot.() -> Unit) = RestaurantListActivityAssertRobot().apply { func() }

class RestaurantListActivityArrangeRobot(private val rule: ActivityTestRule<RestaurantListActivity>) {

    private val sourceResponse by lazy { "restaurants.json".loadResponse() }

    fun launchActivity() {
        rule.launchActivity(Intent())
    }

    fun mockWebServer(mockWebServer: MockWebServer) {
        val dispatcher = object : Dispatcher() {
            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when {
                    request.path.orEmpty().contains("sources") -> {
                        MockResponse().setResponseCode(200).setBody(sourceResponse)
                    }
                    else -> MockResponse().setResponseCode(404)
                }
            }
        }

        mockWebServer.dispatcher = dispatcher
    }
}

class RestaurantListActivityActRobot {
    fun clickPlaceItemByPosition(position: Int) {
        RecyclerViewInteractions.recyclerView(R.id.rv_restaurants) {
            atPosition(position) {
                click()
            }
        }
    }

    fun grantLocationPermission() {
        runtimePermission(Manifest.permission.ACCESS_FINE_LOCATION) {
            allow()
        }
    }
}

class RestaurantListActivityAssertRobot {
    fun isRestaurantListVisible() {
        VisibilityAssertions.displayed {
            id(R.id.rv_restaurants)
        }
    }

    fun verifyItemByPosition(position: Int = 0) {
        RecyclerViewInteractions.recyclerView(R.id.rv_restaurants) {
            atPosition(position) {
                displayed {
                    id(R.id.restaurant_name)
                    id(R.id.restaurant_rating)
                    id(R.id.restaurant_address)
                    id(R.id.restaurant_cuisines)
                }
            }
        }
    }
}