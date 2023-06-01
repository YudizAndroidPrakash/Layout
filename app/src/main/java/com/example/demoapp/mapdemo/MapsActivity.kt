package com.example.demoapp.mapdemo


import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment



import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnMapClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProvideClient: FusedLocationProviderClient
    private lateinit var cbZoom: CheckBox
    private lateinit var cbCurrentLocation: CheckBox
    private lateinit var marker: Marker


//    private lateinit var task: Task<Location>
//    private lateinit var marker: Marker
//    private lateinit var current: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        cbZoom = findViewById(R.id.cb_zoomin_zoomout)
        cbCurrentLocation = findViewById(R.id.cb_move_current_location)


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

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        val updateCurrentLocation = LatLng(currentLocation.latitude, currentLocation.longitude)
//        setMarker(updateCurrentLocation)
        val userCurrentLocation = LatLng(updateCurrentLocation.latitude, updateCurrentLocation.longitude)
        mMap.uiSettings.isMyLocationButtonEnabled = true
//        mMap.uiSettings.

        mMap.animateCamera(CameraUpdateFactory.newLatLng(userCurrentLocation))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userCurrentLocation, 10f))
        mMap.addMarker(MarkerOptions().position(userCurrentLocation).title("Current Location"))

        checkBoxBehaviour(mMap)
    }
    private fun checkBoxBehaviour(googleMap : GoogleMap){
                cbZoom.setOnCheckedChangeListener { _, isChecked ->
                    googleMap.uiSettings.isZoomControlsEnabled = isChecked
            Toast.makeText(this, "$isChecked", Toast.LENGTH_SHORT).show()
        }
        cbCurrentLocation.setOnCheckedChangeListener { _, isChecked ->
            googleMap.uiSettings.isMyLocationButtonEnabled = isChecked
            googleMap.uiSettings.isMyLocationButtonEnabled
            Toast.makeText(this, "$isChecked", Toast.LENGTH_SHORT).show()
        }
    }


//    private fun getLastLocation() {
//        task = fusedLocationProvideClient.lastLocation
//
//        if (ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this, arrayOf(
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ), 1
//            )
//            return
//        }
//
////        fusedLocationProvideClient.lastLocation.addOnSuccessListener { location ->
////            if (location != null) {
////                currentLocation = location
////
////                val mapFragment =
////                    supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
////                mapFragment.getMapAsync { googleMap ->
////                    mMap = googleMap
////
////                    //        task  =fusedLocationProvideClient.lastLocation
////
////                    Toast.makeText(this@MapsActivity, "map is ready", Toast.LENGTH_SHORT).show()
////                    mMap.uiSettings.isZoomControlsEnabled = true
////                    mMap.uiSettings.isMyLocationButtonEnabled = true
////                    mMap.isMyLocationEnabled = true
////
////
////                    // Add a marker in Current Location Move
////                    current = LatLng(currentLocation.latitude, currentLocation.longitude)
////                    mMap.setOnMapClickListener {
////                        val myLocation = LatLng(it.latitude, it.longitude)
////                        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
////                    }
////                    val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
////
////
////                    val list: MutableList<Address> =
////                        geocoder.getFromLocation(
////                            currentLocation.latitude,
////                            currentLocation.longitude,
////                            1
////                        )!!
////                    //        mMap.addMarker(MarkerOptions().position(current).title("Current Location"))
////
////
////                    marker = mMap.addMarker(
////                        MarkerOptions().position(current).title("Current Location")
////                            .snippet("${list[0].getAddressLine(0)},${list[0].locality},${list[0].countryName},${list[0].postalCode}")
////                    )!!
////                    marker.showInfoWindow()
////
////                    mMap.moveCamera(CameraUpdateFactory.newLatLng(current))
////                }
////
////            }
////
////        }
//
//
//    }


//    @SuppressLint("MissingPermission")
//    override fun onMapReady(googleMap: GoogleMap) {
////        task  =fusedLocationProvideClient.lastLocation
//        mMap = googleMap
//        Toast.makeText(this, "map is ready", Toast.LENGTH_SHORT).show()
//        mMap.uiSettings.isZoomControlsEnabled = true
////        mMap.uiSettings.isMyLocationButtonEnabled = true
//
//        mMap.isMyLocationEnabled = true
//
//
//        // Add a marker in Sydney and move the camera
////        val icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_location)
//
//        current = LatLng(currentLocation.latitude, currentLocation.longitude)
//        mMap.setOnMapClickListener {
//            val myLocation = LatLng(it.latitude, it.longitude)
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
//        }

//        val geocoder = Geocoder(this, Locale.getDefault())
//
//
//        val list: MutableList<Address> =
//            geocoder.getFromLocation(currentLocation.latitude, currentLocation.longitude, 1)!!
////        mMap.addMarker(MarkerOptions().position(current).title("Current Location"))
//
//
//        marker = mMap.addMarker(
//            MarkerOptions().position(current).title("Current Location")
//                .snippet("${list[0].getAddressLine(0)},${list[0].locality},${list[0].countryName},${list[0].postalCode}")
//        )!!
//        marker.showInfoWindow()
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(current))
//    }

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

    override fun onMapClick(p0: LatLng) {
        val updateCurrentLocation = LatLng(p0.latitude, p0.longitude)
        setMarker(updateCurrentLocation)
    }

    private fun setMarker(latLng: LatLng) {
        marker.remove()
        val geocoder = Geocoder(this, Locale.getDefault())
        val list: MutableList<Address> =
            geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)!!
        marker = mMap.addMarker(MarkerOptions().position(latLng).title("current location").snippet("${list[0].getAddressLine(0)},${list[0].locality},${list[0].countryName},${list[0].postalCode}"))!!
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))

    }


}