package hu.ecity.highlowgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    var generatedNum = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        if (savedInstanceState != null && savedInstanceState.containsKey("KEY_NUM")) {
            generatedNum = savedInstanceState.getInt("KEY_NUM")
        } else {
            generateRandom()
        }

        btnGuess.setOnClickListener {
            if (etNumber.text.toString().isEmpty()) {
                etNumber.setError("This field can not be empty")
            } else {

                var myNum = etNumber.text.toString().toInt()

                if (myNum < generatedNum) {
                    tvStatus.text = "The number is larger"
                } else if (myNum > generatedNum) {
                    tvStatus.text = "The number is lower"
                } else if (myNum == generatedNum) {
                    tvStatus.text = "You have WON!"

                    startActivity(
                        Intent(this@GameActivity,
                        ResultActivity::class.java)
                    )
                }
            }
        }
    }

    fun generateRandom() {
        val rand = Random(System.currentTimeMillis())
        generatedNum = rand.nextInt(3) // 0..99
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("KEY_NUM", generatedNum)
        super.onSaveInstanceState(outState)
    }
}
