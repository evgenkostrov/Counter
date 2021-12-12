package com.epam.counter

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GPSMonitor (private var context: Context) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun getCurrentLocation() {

        ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQ_CODE
        )
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        )
        {
            Toast.makeText(context, "Haven't permission", Toast.LENGTH_LONG).show()
            return
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQ_CODE = 1000;
    }
}

