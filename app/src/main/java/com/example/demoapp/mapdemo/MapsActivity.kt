package com.example.demoapp.mapdemo


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.CheckBox

import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.demoapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment


import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale


class MapsActivity : AppCompatActivity(), OnMapReadyCallback{



    private lateinit var mMap: GoogleMap
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProvideClient: FusedLocationProviderClient
    private lateinit var cbZoom: CheckBox
    private lateinit var cbCurrentLocation: CheckBox
    private lateinit var marker: Marker
    private lateinit var cbMarker: CheckBox


//    private lateinit var task: Task<Location>
//    private lateinit var marker: Marker
//    private lateinit var current: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        cbZoom = findViewById(R.id.cb_zoomin_zoomout)
        cbCurrentLocation = findViewById(R.id.cb_move_current_location)
        cbMarker = findViewById(R.id.cb_marker_remove)


//        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
//
//        mapFragment.getMapAsync(this)
        fusedLocationProvideClient = LocationServices.getFusedLocationProviderClient(this)
//
//        getLastLocation()
        userCurrentLocation()


    }

    private fun userCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 1
            )
            return
        }

        fusedLocationProvideClient.lastLocation.addOnSuccessListener { location ->

            if (location != null) {
                currentLocation = location
                Toast.makeText(
                    this,
                    "${currentLocation.latitude} ${currentLocation.longitude}",
                    Toast.LENGTH_SHORT
                ).show()
                val mapFragment =
                    supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)
            }

        }

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap) {
        mMap = p0

        try{
            val success = p0.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.map_style_json))
            if(!success){
                Log.e("mapStyle","not loaded")
            }
        }catch (e : Resources.NotFoundException){
            Log.e("Resource","$e")
        }



//        setMarker(updateCurrentLocation)
        val userCurrentLocation = LatLng(currentLocation.latitude, currentLocation.longitude)
//        mMap.uiSettings.isMyLocationButtonEnabled = true
//        mMap.isMyLocationEnabled = true

        val geocoder = Geocoder(this, Locale.getDefault())
        val list: MutableList<Address> =
            geocoder.getFromLocation(currentLocation.latitude, currentLocation.longitude, 1)!!
//        mMap.uiSettings.
        marker = mMap.addMarker(
            MarkerOptions().position(userCurrentLocation).title("current location")
                .snippet("${list[0].getAddressLine(0)},${list[0].locality},${list[0].countryName},${list[0].postalCode}")
        )!!
        mMap.animateCamera(CameraUpdateFactory.newLatLng(userCurrentLocation))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userCurrentLocation, 10f))



        checkBoxBehaviour(mMap)

        mMap.setOnMapClickListener {
            val updateCurrentLocation = LatLng(it.latitude, it.longitude)
            marker.remove()
            val geocoderLocation = Geocoder(this, Locale.getDefault())
            val addressList: MutableList<Address> =
                geocoderLocation.getFromLocation(it.latitude, it.longitude, 1)!!
            marker = mMap.addMarker(
                MarkerOptions().position(updateCurrentLocation).title("current location")
                    .snippet("${addressList[0].getAddressLine(0)},${addressList[0].locality},${addressList[0].countryName},${addressList[0].postalCode}")
            )!!
            mMap.animateCamera(CameraUpdateFactory.newLatLng(updateCurrentLocation))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(updateCurrentLocation, 10f))

        }
    }

    @SuppressLint("MissingPermission")
    private fun checkBoxBehaviour(googleMap: GoogleMap) {
        cbZoom.setOnCheckedChangeListener { _, isChecked ->
            googleMap.uiSettings.isZoomControlsEnabled = isChecked
        }
        cbCurrentLocation.setOnCheckedChangeListener { _, isChecked ->
            googleMap.uiSettings.isMyLocationButtonEnabled = isChecked
        }
        cbMarker.setOnCheckedChangeListener { _, isChecked ->
            googleMap.isMyLocationEnabled = isChecked
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            userCurrentLocation()
        } else {
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.map_type, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val mapStyle = when (item.itemId) {
            R.id.map_none -> {
                GoogleMap.MAP_TYPE_NONE
            }
            R.id.map_hybrid -> {
                GoogleMap.MAP_TYPE_HYBRID
            }
            R.id.map_normal -> {
                GoogleMap.MAP_TYPE_NORMAL
            }
            R.id.map_satelite -> {
                GoogleMap.MAP_TYPE_SATELLITE
            }
            R.id.map_terrain -> {
                GoogleMap.MAP_TYPE_TERRAIN
            }
            else -> {
                R.id.map_normal
            }
        }
        mMap.mapType = mapStyle
        return true
    }
}


