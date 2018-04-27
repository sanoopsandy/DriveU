package com.example.sanoop.driveu.modules

import android.app.Application
import android.content.Context
import android.location.LocationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideApplication() : Application{
        return application
    }

    @Provides
    @Singleton
    fun provideLocationManager(): LocationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager


}