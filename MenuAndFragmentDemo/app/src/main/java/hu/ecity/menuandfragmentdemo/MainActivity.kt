package hu.ecity.menuandfragmentdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
    }

    // Itt mondjuk meg, hogy melyik menü erőforrást töltse be, magyarul milyen menüpontjaink legyenek
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Ez hívódik meg amikor valamelyik menü elemet kiválasztottuk
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_main) {

            startActivity(Intent(this, MainPagerActivity::class.java))


        } else if (item.itemId == R.id.action_details) {
            startActivity(Intent(this, DetailsActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

}
