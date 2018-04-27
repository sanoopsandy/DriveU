package com.example.sanoop.driveu.networkManager

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.sanoop.driveu.MapApplication
import com.example.sanoop.driveu.models.LocationExplorer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NetworkDataManager {

    @Inject
    lateinit var iWebService: IWebService

    fun getLocationData(): LiveData<LocationExplorer> {
        MapApplication.netComponent.inject(this)
        val data = MutableLiveData<LocationExplorer>()
        iWebService.getLocationData().enqueue(object : Callback<LocationExplorer> {
            override fun onFailure(call: Call<LocationExplorer>?, t: Throwable?) {
                data.value = null
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<LocationExplorer>, response: Response<LocationExplorer>) {
                val body = response.body()
                val locationExplorer = body!!
                data.value = locationExplorer
            }

        })

        return data
    }

}