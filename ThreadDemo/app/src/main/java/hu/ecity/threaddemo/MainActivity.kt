package hu.ecity.threaddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var enabled: Boolean = false

    inner class MyThread : Thread() {
        override fun run() {
            while (enabled) {
                runOnUiThread {
                    tvData.append("#")
                }

                sleep(1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            enabled = true
            MyThread().start()

            tvData.append("HELLO")
        }

        btnStop.setOnClickListener {
            enabled = false
        }
    }

    override fun onStop() {
        enabled = false
        super.onStop()
    }

}
