package com.example.sanoop.driveu.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.sanoop.driveu.models.LocationExplorer
import com.example.sanoop.driveu.networkManager.NetworkDataManager

class LocationExplorerViewModel(application: Application) : AndroidViewModel(application) {

    private val locationExplorerLiveData: LiveData<LocationExplorer>
    private val networkManager = NetworkDataManager()
    init {
        locationExplorerLiveData = networkManager.getLocationData()
    }

    fun getLocationUpdates(): LiveData<LocationExplorer> {
        return networkManager.getLocationData()
    }

}