package com.example.testcalculator.map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.testcalculator.R
import com.example.testcalculator.model.Data
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var id  = 0

    private lateinit var gMap: GoogleMap

   private lateinit var  data :Data

    private lateinit var mapsController: MapsController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        id = intent.getIntExtra("id", 0)

        mapsController = MapsController(this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)


    }


    private fun initMapConfig(googleMap: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            showAttentionDialog()
        } else {
            gMap = googleMap
            gMap.setPadding(0, 0, 0, 0)
            gMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            gMap.isMyLocationEnabled = true
            gMap.uiSettings?.isCompassEnabled = true
            gMap.uiSettings?.isMyLocationButtonEnabled = true
            gMap.uiSettings?.isZoomGesturesEnabled = true
            gMap.uiSettings?.isRotateGesturesEnabled = false
            gMap.uiSettings?.isZoomControlsEnabled = true
        }
    }

    private fun movingCenter(latlng: LatLng) {

        val cameraPosition = CameraPosition.Builder()
            .target(latlng)
            .zoom( 15F)
            .build()
        gMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        gMap.addMarker(MarkerOptions().position(latlng))
    }

    private fun showAttentionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("Perhatian!")
            setMessage("Izin lokasi belum diberikan. Aktifkan izin lokasi?")
            setPositiveButton("ok") { dialog, _ ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
                dialog.dismiss()
            }
        }

        builder.create().show()
    }

    override fun onMapReady(p0: GoogleMap?) {
        p0?.let { initMapConfig(it) }

        launch {

            data = mapsController.getDataById(id)
            movingCenter(LatLng(data.lat!!.toDouble(), data.long!!.toDouble()))

        }


    }

    fun launch(context: CoroutineContext = Dispatchers.Main, block: suspend () -> Unit) {
        CoroutineScope(context).launch {
            block()
        }
    }
}
