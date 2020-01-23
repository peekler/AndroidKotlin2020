package hu.ecity.musicplaydemo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {

    lateinit var myPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            myPlayer = MediaPlayer.create(this@MainActivity,
                R.raw.music)
            myPlayer.setOnPreparedListener(this@MainActivity)
        }

        btnStop.setOnClickListener {
            if (myPlayer != null) {
                myPlayer.stop()
                //myPlayer.seekTo(20000)
            }
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }


    override fun onStop() {
        if (myPlayer != null) {
            myPlayer.stop()
        }

        super.onStop()
    }

}
