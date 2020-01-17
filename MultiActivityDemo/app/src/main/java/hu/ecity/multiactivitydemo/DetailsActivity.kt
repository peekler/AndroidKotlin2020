package hu.ecity.multiactivitydemo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var data = intent.getStringExtra(MainActivity.KEY_DATA)
        tvData.setText(data)

        btnOk.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish() // leveszi az Activity-t a backstackről
        }

        btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish() // leveszi az Activity-t a backstackről
        }
    }


    override fun onBackPressed() {
        Toast.makeText(this,
            "HEHE, nem lépsz ki!", Toast.LENGTH_LONG).show()
    }


}
