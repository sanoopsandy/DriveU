package com.example.sanoop.driveu.networkManager

import com.example.sanoop.driveu.models.LocationExplorer
import retrofit2.Call
import retrofit2.http.GET

interface IWebService {

    @GET("/explore")
    fun getLocationData(): Call<LocationExplorer>

}