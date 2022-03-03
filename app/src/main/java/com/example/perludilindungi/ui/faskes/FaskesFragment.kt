package com.example.perludilindungi.ui.faskes

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.perludilindungi.R
import com.example.perludilindungi.databinding.FragmentFaskesBinding
import com.example.perludilindungi.models.ProvinceResponse
import com.example.perludilindungi.repository.Repository
import com.example.perludilindungi.ui.list_faskes.ListFaskesFragmentFactory
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_faskes.*
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class FaskesFragment : Fragment(R.layout.fragment_faskes) {

    private var _binding: FragmentFaskesBinding? = null
    private lateinit var viewModel: FaskesViewModel
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        getNewLocation()
        getLastLocation()
        Log.d("Debug", viewModel.latitude.toString())
        Log.d("Debug", viewModel.longitude.toString())
        if (binding.autoCompleteTextViewProvince.text == null || binding
                .autoCompleteTextViewProvince.text.toString() == ""
        ) return
        viewModel.getCity(binding.autoCompleteTextViewProvince.text.toString())
        viewModel.cityResponse.observe(viewLifecycleOwner) { response ->
            viewModel.cities.clear()
            viewModel.cities.addAll(response.results.map { it.key }
                .toList())
            val arrayAdapter = ArrayAdapter(
                requireContext(), R.layout
                    .item_text, viewModel.cities
            )
            Log.d(
                "Response", response.results.toString()
            )
            binding.autoCompleteTextViewCity.setAdapter(arrayAdapter)
        }
        setupRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = Repository()
        val viewModelFactory = FaskesViewModelFactory(repository)
        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            )[FaskesViewModel::class.java]
        _binding = FragmentFaskesBinding.inflate(inflater, container, false)
        fusedLocationProviderClient = LocationServices
            .getFusedLocationProviderClient(activity)
        val root: View = binding.root


        viewModel.getProvince()
        viewModel.provResponse.observe(viewLifecycleOwner) { response ->
            fetchProvince(response)
        }

        binding.autoCompleteTextViewProvince.setOnItemClickListener { adapterView, view, i, l ->
            fetchCity()
        }

        binding.autoCompleteTextViewCity.setOnItemClickListener { adapterView, view, i, l ->
//            fetchFaskes()
        }

        binding.faskesSearchButton.setOnClickListener {
            fetchFaskes()
        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun fetchProvince(response: ProvinceResponse) {
        viewModel.provinces.addAll(response.results.map { it.key }
            .toList())
        val arrayAdapter = ArrayAdapter(
            requireContext(), R.layout
                .item_text, viewModel.provinces
        )
        binding.autoCompleteTextViewProvince.setAdapter(arrayAdapter)
    }


    private fun fetchCity() {
        viewModel.getCity(binding.autoCompleteTextViewProvince.text.toString())
        viewModel.cityResponse.observe(viewLifecycleOwner) { response ->
            viewModel.cities.clear()
            viewModel.cities.addAll(response.results.map { it.key }
                .toList())
            val arrayAdapter = ArrayAdapter(
                requireContext(), R.layout
                    .item_text, viewModel.cities
            )
            Log.d(
                "Response", response.results.toString()
            )
            binding.autoCompleteTextViewCity.setAdapter(arrayAdapter)
            binding.autoCompleteTextViewCity.text.clear()
        }
    }

    private fun fetchFaskes() {
        val prov: String =
            binding.autoCompleteTextViewProvince.text.toString()
        viewModel.getDaftarFaskes(
            prov,
            autoCompleteTextViewCity.text.toString()
        )
        viewModel.faskesResponse.observe(viewLifecycleOwner) { response ->
            viewModel.daftarFaskes.clear()
            viewModel.daftarFaskes.addAll(response.data.sortedBy {
                distance(
                    viewModel.latitude, viewModel.longitude,
                    it.latitude.toDouble(), it.longitude.toDouble()
                )
            }
                .take(5))
            setupRecyclerView()
        }
    }

    fun distance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        val theta = lon1 - lon2
        var dist =
            sin(deg2rad(lat1)) * sin(deg2rad(lat2)) + cos(deg2rad(lat1)) * cos(
                deg2rad(lat2)
            ) * cos(deg2rad(theta))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        dist *= 1.609344
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }

    private fun setupRecyclerView() {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTsx = fragmentManager?.beginTransaction()
        fragmentTsx?.replace(
            binding.fragmentContaionerRview.id,
            ListFaskesFragmentFactory().newFragment(viewModel.daftarFaskes,
                "Faskes")
        )
        fragmentTsx?.commit()
        if (fragmentTsx == null) {
            Log.d("Debug", "Null fragmentTSX")
        }
    }

    private fun checkGPSPermission(): Boolean {
        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED ||
            activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermission() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 7070
            )
        }
    }

    private fun isServiceEnabled(): Boolean {
        val locationManager = activity?.getSystemService(LOCATION_SERVICE) as
                LocationManager
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
        if (requestCode == 7070) {
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
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                var location: Location? = task.result
                if (location != null) {
                    viewModel.latitude = location.latitude
                    viewModel.longitude = location.longitude
                }
                else {
                    getNewLocation()
                }
            }
        }
        else {
            Toast.makeText(
                activity, "Please enable your location service", Toast
                    .LENGTH_SHORT
            )
                .show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getNewLocation() {
        val locationRequest = LocationRequest.create()
            .apply {
                interval = 100
                fastestInterval = 50
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                maxWaitTime = 100
            }
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            val lastLoc: Location = p0.lastLocation
            viewModel.latitude = lastLoc.latitude.toDouble()
            viewModel.longitude = lastLoc.longitude.toDouble()
        }
    }

}