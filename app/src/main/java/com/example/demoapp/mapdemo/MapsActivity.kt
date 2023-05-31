package com.example.demoapp.mapdemo

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.demoapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.UiSettings
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory


import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import java.util.Locale


class MapsActivity : AppCompatActivity(){

    private lateinit var mMap: GoogleMap
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProvideClient: FusedLocationProviderClient

    private lateinit var task: Task<Location>
    private lateinit var marker: Marker
    private lateinit var current: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        fusedLocationProvideClient = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()


    }

    private fun getLastLocation() {
        task = fusedLocationProvideClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
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

                val mapFragment =
                    supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync { googleMap ->
                    mMap = googleMap

                    //        task  =fusedLocationProvideClient.lastLocation

                    Toast.makeText(this@MapsActivity, "map is ready", Toast.LENGTH_SHORT).show()
                    mMap.uiSettings.isZoomControlsEnabled = true
                    mMap.uiSettings.isMyLocationButtonEnabled = true
                    mMap.isMyLocationEnabled = true


                    // Add a marker in Current Location Move
                    current = LatLng(currentLocation.latitude, currentLocation.longitude)
                    mMap.setOnMapClickListener {
                        val myLocation = LatLng(it.latitude, it.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
                    }
                    val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())


                    val list: MutableList<Address> =
                        geocoder.getFromLocation(
                            currentLocation.latitude,
                            currentLocation.longitude,
                            1
                        )!!
                    //        mMap.addMarker(MarkerOptions().position(current).title("Current Location"))


                    marker = mMap.addMarker(
                        MarkerOptions().position(current).title("Current Location")
                            .snippet("${list[0].getAddressLine(0)},${list[0].locality},${list[0].countryName},${list[0].postalCode}")
                    )!!
                    marker.showInfoWindow()

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(current))
                }

            }

        }
//        task.addOnSuccessListener { p0 ->
//            if (p0 != null) {
//                currentLocation = p0
//                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//                     }
//        }
//        task.addOnCompleteListener {task ->
//            if(task.isSuccessful && task.result != null){
//
//
//            }
//
//        }


    }


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
            getLastLocation()
        } else {
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.map_type,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mMap.mapType = item.itemId
       return  true
    }
}