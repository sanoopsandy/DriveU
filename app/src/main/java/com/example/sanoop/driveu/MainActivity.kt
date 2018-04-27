package com.example.sanoop.driveu

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction

class MainActivity : FragmentActivity() {

    private val RECORD_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MapApplication.netComponent.inject(this)
        init()
    }

    private fun checkPermissions() {
        val PERMISSIONS = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
        if (!hasPermissions(PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, RECORD_REQUEST_CODE)
        }
    }

    private fun hasPermissions(permissions: Array<String>): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissions.forEach {
                if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true
    }

    private fun init() {
        val tag = "Maps"
        checkPermissions()
        val transaction: FragmentTransaction = supportFragmentManager
                .beginTransaction()
                .add(R.id.mainContainer, MapFragment(), tag)
        transaction.commit()

    }
}
