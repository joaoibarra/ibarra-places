package com.ibarra.places.ui.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ibarra.places.data.db.dao.RestaurantDao
import com.ibarra.places.data.db.entity.Restaurant
import com.ibarra.places.data.remote.IbarraPlacesApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RestaurantListViewModel(private val ibarraPlacesApi: IbarraPlacesApi,
                              private val dao: RestaurantDao): ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    var items: LiveData<PagedList<Restaurant>>? = null
    var factory: DataSource.Factory<Int, Restaurant>? = null
    var latitude: Double? = null
    var longitude: Double? = null
    val error = MutableLiveData<Boolean>()
    val progress= MutableLiveData<Boolean>()
    var resultsStart: Int = 0

    init {
        initPagedList()
    }

    private fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun getNearbyLocation() {
        error.postValue(false)
        if(latitude!=null && longitude!=null) {
            addToDisposable(
                ibarraPlacesApi.getRestaurants(latitude.toString(), longitude.toString(), resultsStart)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { showProgress() }
                    .doOnSuccess{ hideProgress() }
                    .subscribe({
                        error.postValue(false)
                        resultsStart = it.resultsShown
                        dao.insertAll(Restaurant.toList(it.restaurants))
                    }, {
                        error.postValue(true)
                    })
            )
        }
    }

    private fun initPagedList() {
        factory = dao.findAll()
        factory?.let {
            val pagedListBuilder: LivePagedListBuilder<Int, Restaurant>  = LivePagedListBuilder<Int, Restaurant>(it,
                20)
            items = pagedListBuilder
                .setBoundaryCallback(RestaurantListBoundaryCallback(this))
                .build()
        }
    }

    private fun showProgress() {
        if(items?.getValue().isNullOrEmpty()) {
            progress.postValue(true)
        }
    }

    private fun hideProgress() {
        progress.postValue(false)
    }

    fun updateLocation(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
        getNearbyLocation()
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}