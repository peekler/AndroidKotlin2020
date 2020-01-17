package hu.ecity.viewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val cityNames = arrayOf(
        "Dunaújváros", "Dunaföldvár", "Dunavecse",
        "New York", "New Orleans"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            cityNames
        )
        autoCity.setAdapter(cityAdapter)
        autoCity.threshold = 1

        rbRed.setOnClickListener {
            layoutMain.setBackgroundColor(Color.RED)
        }
        rbBlue.setOnClickListener {
            layoutMain.setBackgroundColor(Color.BLUE)
        }
        rbWhite.setOnClickListener {
            layoutMain.setBackgroundColor(Color.WHITE)
        }


        val fruitsAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.fruits_array,
            android.R.layout.simple_spinner_item
        )
        fruitsAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerFruits.adapter = fruitsAdapter

        //spinnerFruits.onItemSelectedListener..
    }
}
