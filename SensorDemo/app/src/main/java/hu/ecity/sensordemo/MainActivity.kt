package hu.ecity.sensordemo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val sensorAccelero = sensorManager.getDefaultSensor(
            Sensor.TYPE_MAGNETIC_FIELD)
        sensorManager.registerListener(this,
            sensorAccelero, SensorManager.SENSOR_DELAY_NORMAL
        )

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent) {
        /*var accX = event.values[0].toDouble()
        var accY = event.values[1].toDouble()
        var accZ = event.values[2].toDouble()

        tvData.text = "X: $accX\nY: $accY\nZ: $accZ"*/

        tvData.text = "Magnet: ${event.values[0]}"
    }
}
