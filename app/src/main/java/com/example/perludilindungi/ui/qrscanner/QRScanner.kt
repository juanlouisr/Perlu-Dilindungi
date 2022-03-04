package com.example.perludilindungi.ui.qrscanner

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.perludilindungi.R
import com.example.perludilindungi.RetrofitClient
import com.example.perludilindungi.databinding.ActivityQrscannerBinding
import com.google.android.gms.location.*
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class QRScanner : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var temperature: Sensor? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude: Float = 10.toFloat()
    private var longitude: Float = 10.toFloat()
    private lateinit var binding: ActivityQrscannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrscannerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        binding.degree.text = "${temperature.toString()}\u2103"

        binding.qrButton.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setDesiredBarcodeFormats(listOf(IntentIntegrator.QR_CODE))
            intentIntegrator.initiateScan()
        }

        binding.backButton.setOnClickListener{
            onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(resultCode, data)
        if (result.contents != null) {
            // get location
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            getNewLocation()
            getLastLocation()

            //post check in
            val jsonObject = JSONObject()
            jsonObject.put("qrCode", result.contents)
            jsonObject.put("latitude", latitude.toString())
            jsonObject.put("longitude", longitude.toString())

            // Convert JSONObject to String
            val jsonObjectString = jsonObject.toString()

            // Create RequestBody
            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())


            val service = RetrofitClient.checkInInstance
            CoroutineScope(Dispatchers.IO).launch {
                // Do the POST request and get response
                val response = service.postCheckIn(requestBody)

                withContext(Dispatchers.Main) {
                    if (response.success == true) {
                        Log.d("Hasil", response.data.toString())
                        if (response.data.userStatus == "green"){
                            binding.statusImage.setImageResource(R.drawable.ic_resource_true)
                            binding.status.text = "Berhasil"
                            binding.keterangan.text = response.data.reason
                        }
                        else{
                            binding.statusImage.setImageResource(R.drawable.ic_resource_false)
                            binding.status.text = "Gagal"
                            binding.keterangan.text = response.data.reason
                        }
                    } else {
                        binding.statusImage.setImageResource(R.drawable.ic_resource_false)
                        binding.status.text = "Gagal"
                        binding.keterangan.text = response.message
                    }
                }
            }
        }
    }

    override fun onSensorChanged(s0: SensorEvent?) {
        val temp: TextView = findViewById(R.id.degree)
        if (s0 != null) {
            temp.text = "${s0.values[0].toString()}\u2103"
        }
    }

    override fun onAccuracyChanged(s0: Sensor?, p1: Int) {
    }

    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()
        sensorManager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    private fun checkGPSPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), 6969
        )
    }

    private fun isServiceEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 6969) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug:", "Permission Granted")
            }
        }
    }

    private fun getLastLocation() {
        while (!checkGPSPermission()) {
            requestPermission()
        }
        if (isServiceEnabled()) {
            fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                var location: Location? = task.result
                if (location != null) {
                    latitude = location.latitude.toFloat()
                    longitude = location.longitude.toFloat()
                } else {
                    getNewLocation()
                }
            }
        } else {
            Toast.makeText(this, "Please enable your location service", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getNewLocation() {
        val locationRequest = LocationRequest.create().apply {
            interval = 100
            fastestInterval = 50
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            maxWaitTime = 100
        }
        fusedLocationClient!!.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            var lastLoc: Location = p0.lastLocation
            latitude = lastLoc.latitude.toFloat()
            longitude = lastLoc.longitude.toFloat()
        }
    }
}