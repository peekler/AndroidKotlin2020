package hu.ecity.animationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendAnim = AnimationUtils.loadAnimation(
            this@MainActivity, R.anim.send_anim
        )

        btnAnim.setOnClickListener {

            tvText.startAnimation(sendAnim)
        }

        sendAnim.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                Toast.makeText(this@MainActivity,
                    "Message sent", Toast.LENGTH_LONG).show()
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })

    }
}
