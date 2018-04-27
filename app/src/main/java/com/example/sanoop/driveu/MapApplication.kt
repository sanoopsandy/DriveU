package com.example.sanoop.driveu

import android.app.Application
import android.location.LocationManager
import android.util.Log
import com.example.sanoop.driveu.components.DaggerNetComponent
import com.example.sanoop.driveu.components.NetComponent
import com.example.sanoop.driveu.modules.AppModule
import com.example.sanoop.driveu.modules.NetModule
import javax.inject.Inject

class MapApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var netComponent: NetComponent
    }

    override fun onCreate() {
        super.onCreate()
        netComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule("http://192.168.84.79:8080"))
                .build()
    }
}