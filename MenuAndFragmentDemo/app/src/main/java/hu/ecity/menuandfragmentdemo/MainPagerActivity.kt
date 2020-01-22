package hu.ecity.menuandfragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ecity.menuandfragmentdemo.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.activity_main_pager.*

class MainPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pager)

        viewpager.adapter = MyPagerAdapter(supportFragmentManager)
    }
}
