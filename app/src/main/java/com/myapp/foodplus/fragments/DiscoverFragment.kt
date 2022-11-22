package com.myapp.foodplus.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.myapp.foodplus.R


class DiscoverFragment : Fragment(), OnMapReadyCallback {

    private var isPermissionGranted: Boolean = false

    override fun onMapReady(googleMap: GoogleMap) {

        // Add a marker in Sydney and move the camera
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

        checkMyPermission()

        if (isPermissionGranted) {
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(this)
            val myLocationButton: View? = mapFragment?.view?.findViewById(0x2)

            if (myLocationButton != null && myLocationButton.layoutParams is RelativeLayout.LayoutParams) {
                // location button is inside of RelativeLayout
                val params = myLocationButton.layoutParams as RelativeLayout.LayoutParams

                // Align it to - parent BOTTOM|LEFT
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0)
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)

                // Update margins, set to 10dp
                val margin = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10f,
                    resources.displayMetrics
                ).toInt()
                params.setMargins(margin, margin, margin, margin)
                myLocationButton.layoutParams = params
            }
        }
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