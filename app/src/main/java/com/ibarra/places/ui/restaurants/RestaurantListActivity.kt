package com.ibarra.places.ui.restaurants

import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.ibarra.places.R
import com.ibarra.places.databinding.ActivityRestaurantListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantListActivity : AppCompatActivity() {

    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private val restaurantListViewModel: RestaurantListViewModel by viewModel()

    companion object {
        const val ACCESS_LOCATION_PERMISSION = 27
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val binding: ActivityRestaurantListBinding = DataBindingUtil.setContentView(this@RestaurantListActivity, R.layout.activity_restaurant_list)

        binding.viewModel = restaurantListViewModel
        binding.lifecycleOwner = this

        restaurantListViewModel.pageToStart.observe(this, Observer { url ->
            openRestaurantAtBrowser(url)
        })
    }

    override fun onStart() {
        super.onStart()
        checkUserPermission()
    }

    private fun checkUserPermission() {
        if (ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION) ,
                ACCESS_LOCATION_PERMISSION )

        } else {
            getUserLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == ACCESS_LOCATION_PERMISSION
            && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getUserLocation()

        }
    }

    private fun getUserLocation() {
        fusedLocationProviderClient?.lastLocation?.addOnSuccessListener { location : Location? ->
            location?.let {
                restaurantListViewModel.updateLocation(location.latitude,location.longitude)
            }
        }?.addOnFailureListener {

        }
    }

    private fun openRestaurantAtBrowser(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}
