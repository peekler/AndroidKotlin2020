package hu.ecity.locationdemo

import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MyLocationProvider.OnNewLocationAvailable {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvLocation.setOnClickListener {
            if (prevLocation != null){
                Thread {
                    try {
                        val gc = Geocoder(this, Locale.getDefault())
                        var addrs: List<Address> =
                            gc.getFromLocation(prevLocation!!.latitude, prevLocation!!.longitude, 3)
                        val addr =
                            "${addrs[0].getAddressLine(0)}, ${addrs[0].getAddressLine(1)}," +
                                    " ${addrs[0].getAddressLine(2)}"

                        runOnUiThread {
                            Toast.makeText(this, addr, Toast.LENGTH_LONG).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        runOnUiThread {
                            Toast.makeText(
                                this@MainActivity,
                                "Error: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }.start()
            }
        }


        requestNeededPermission()
    }

    private lateinit var myLocationProvider: MyLocationProvider

    fun startLocationMonitoring() {
        myLocationProvider = MyLocationProvider(this,
            this)
        myLocationProvider.startLocationMonitoring()
    }

    override fun onStop() {
        super.onStop()
        myLocationProvider.stopLocationMonitoring()
    }

    private fun requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                Toast.makeText(
                    this,
                    "I need it for location", Toast.LENGTH_SHORT
                ).show()
            }

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        } else {
            startLocationMonitoring()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "ACCESS_FINE_LOCATION perm granted", Toast.LENGTH_SHORT)
                        .show()

                    startLocationMonitoring()
                } else {
                    Toast.makeText(
                        this,
                        "ACCESS_FINE_LOCATION perm NOT granted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    var prevLocation:Location? = null
    var distance = 0.0

    override fun onNewLocation(location: Location) {

        if (prevLocation != null) {
            if (location.distanceTo(prevLocation) > 10) {
                distance += location.distanceTo(prevLocation)
            }
        }
        prevLocation = location

        var locationText =
            "Latitude: ${location.latitude}\n" +
            "Longitude: ${location.longitude}\n"+
            "Distance: $distance m"



        tvLocation.text = locationText
    }

}
