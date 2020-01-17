package hu.ecity.multiactivitydemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_DATA = "KEY_DATA"
        const val REQUEST_DETAILS = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStart.setOnClickListener {
            var detailsIntent = Intent()
            detailsIntent.setClass(
                this@MainActivity,
                DetailsActivity::class.java
            )
            detailsIntent.putExtra(MainActivity.KEY_DATA,
                etData.text.toString())
            startActivityForResult(detailsIntent, REQUEST_DETAILS)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_DETAILS) {
            if (resultCode == Activity.RESULT_OK) {
                tvStatus.text = "ALL OK"
            } else if (resultCode == Activity.RESULT_CANCELED) {
                tvStatus.text = "CANCELLED"
            }
        }
    }

}
