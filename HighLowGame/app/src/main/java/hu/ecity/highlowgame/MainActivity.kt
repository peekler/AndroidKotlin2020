package hu.ecity.highlowgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            startActivity(
                Intent(this@MainActivity,
                    GameActivity::class.java)
            )
        }

        btnAbout.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                "@eCity",
                Toast.LENGTH_LONG
            ).show()
        }

        btnHelp.setOnClickListener {
            Snackbar.make(
                layoutMain,
                "Press start to begin",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
