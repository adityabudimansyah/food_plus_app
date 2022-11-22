package com.myapp.foodplus.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.myapp.foodplus.R
import com.myapp.foodplus.activities.RestaurantDetailActivity
import com.myapp.foodplus.adaters.RestaurantAdapter
import com.myapp.foodplus.models.RestaurantData


class DiscoverFragment : Fragment(), OnMapReadyCallback {

    private var isPermissionGranted: Boolean = false
    private var  myLocationButton: View? = null

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.isMyLocationEnabled = true
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-7.793358, 110.370479), 12f))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize google maps
        checkMyPermission()
        if (isPermissionGranted) {
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(this)
            myLocationButton = mapFragment?.view?.findViewById(0x2)

            // Remove the default my location button
            if (myLocationButton != null ) {
               myLocationButton?.visibility = View.GONE
            }
        }

        // Configure bottom sheet
        val bottomSheet = view.findViewById<ConstraintLayout>(R.id.bottomSheet)
        BottomSheetBehavior.from(bottomSheet).apply {
            peekHeight = 210
            this.state = BottomSheetBehavior.STATE_EXPANDED
            // ToDo : set bottom sheet jd expanded ketika radio button di klik (dan ubah tvDescription nya)
        }

        // Configure myLocation Button
        view.findViewById<FloatingActionButton>(R.id.fabMyLocation).setOnClickListener {
            if(myLocationButton != null)
                myLocationButton?.callOnClick()
        }

        // Configure Restaurants Recycler View
        val restaurantDataList = ArrayList<RestaurantData>()
        restaurantDataList.addAll(listRestaurantData)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_restaurant)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = true
        recyclerView.adapter = RestaurantAdapter(requireContext(), restaurantDataList) {
            val intent = Intent (context, RestaurantDetailActivity::class.java)
            intent.putExtra("OBJECT_INTENT", it)
            startActivity(intent)
        }
    }

    private val listRestaurantData: ArrayList<RestaurantData>
        get() {
            val dataName = resources.getStringArray(R.array.data_restaurant_name)
            val dataHighlight = resources.getStringArray(R.array.data_restaurant_highlight)
            val dataPhoto = resources.obtainTypedArray(R.array.data_restaurant_photo)
            val dataDesc = resources.getStringArray(R.array.data_restaurant_description)

            val lists = ArrayList<RestaurantData>()
            for (i in 1..5) { // set data restaurant bakery menjadi 5 ke dalam list
                for (i in dataName.indices) {
                    val restaurantData = RestaurantData(
                        dataName[i], dataHighlight[i], dataPhoto.getResourceId(i, -1), dataDesc[i], 0.0, 0.0
                    )
                    lists.add(restaurantData)
                }
            }
            return lists
        }

    private fun checkMyPermission() {
        Dexter.withContext(this@DiscoverFragment.activity)
            .withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
//                    Snackbar.make(view!!.findViewById(R.id.discoverLayout), "Permission Granted", Snackbar.LENGTH_LONG).show()
//                    Toast.makeText(this@DiscoverFragment.activity, "Permission Granted", Toast.LENGTH_LONG).show()
                    isPermissionGranted = true
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri: Uri = Uri.fromParts("package", context!!.packageName, "")
                    intent.data = uri

                    Toast.makeText(this@DiscoverFragment.activity, "Turn on your location permission", Toast.LENGTH_LONG).show()
                    startActivity(intent)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).check()
    }

}