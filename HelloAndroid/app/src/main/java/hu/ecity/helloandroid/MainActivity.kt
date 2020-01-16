package hu.ecity.helloandroid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    var num: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTime.setOnClickListener {
            var name = etName.text.toString()
            var currentTime =
                    Date(System.currentTimeMillis()).toString()

            var myCar = Car("Opel")

            Toast.makeText(
                    this@MainActivity,
                    "Hello: ${myCar.type}, a dátum: $currentTime",
                    Toast.LENGTH_LONG
            ).show()
        }
    }

}
