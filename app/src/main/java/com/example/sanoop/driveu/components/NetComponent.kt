package com.example.sanoop.driveu.components

import com.example.sanoop.driveu.MainActivity
import com.example.sanoop.driveu.MapApplication
import com.example.sanoop.driveu.MapFragment
import com.example.sanoop.driveu.modules.AppModule
import com.example.sanoop.driveu.modules.NetModule
import com.example.sanoop.driveu.networkManager.NetworkDataManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface NetComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(networkDataManager: NetworkDataManager)
//    fun inject(application: MapApplication)
}