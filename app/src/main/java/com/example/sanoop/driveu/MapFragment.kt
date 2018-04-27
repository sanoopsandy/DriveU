package com.example.sanoop.driveu

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sanoop.driveu.databinding.MapFragmentBinding
import com.example.sanoop.driveu.viewModels.LocationExplorerViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class MapFragment : Fragment(), OnMapReadyCallback {

    var floatingFlag: Boolean = false
    lateinit var btnClick: FloatingActionButton
    lateinit var map: GoogleMap
    private lateinit var locationViewModel: LocationExplorerViewModel
    private lateinit var handler: Handler
    private lateinit var binding: MapFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<MapFragmentBinding>(inflater, R.layout.map_fragment, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        binding.floatingFlag = floatingFlag
        mapFragment.getMapAsync(this)
        handler = Handler()
        locationViewModel = ViewModelProviders.of(this).get(LocationExplorerViewModel::class.java)
        btnClick = binding.btnClick
        return binding.root
    }

    override fun onMapReady(map1: GoogleMap?) {
        map = map1!!
        val marker = LatLng(12.83, 77.677)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 4.0f))
        btnClick.setOnClickListener({ observeFloatingButton() })
    }

    private fun observeFloatingButton() {
        floatingFlag = !floatingFlag
        val count = 0
        val job = launch {
            while (floatingFlag) {
                Log.i("Sandy", "$count++")
                fetchLocationUpdates()
                delay(4000)
            }
        }
        if (!floatingFlag) {
            job.cancel()
            btnClick.background = ContextCompat.getDrawable(context!!, R.drawable.ic_play)
        } else {
            btnClick.background = ContextCompat.getDrawable(context!!, R.drawable.ic_stop)
        }
        binding.floatingFlag = floatingFlag
        Log.i("Sandy", "out of loop")
    }

    private fun fetchLocationUpdates() {
        locationViewModel.getLocationUpdates().observe(this, Observer { location ->
            if (location != null && location.status.equals("success")) {
                handler.post {
                    Log.i("Sandy", "lat : ${location.latitude} long : ${location.longitude}")
                    val marker = LatLng(location.latitude, location.longitude)
                    map.addMarker(MarkerOptions().position(marker).title("Dope stuff + ${location.latitude}"))
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 12.0f))
                }

            } else {
                Toast.makeText(activity, "Check you connection", Toast.LENGTH_LONG).show()
            }
        })
    }
}