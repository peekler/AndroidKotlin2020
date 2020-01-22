package hu.ecity.menuandfragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        navigation.setOnNavigationItemSelectedListener(myOnNavigationItemSelectedListener)

        showFragmentByTag(FragmentHome.TAG, false)
    }

    public fun showFragmentByTag(tag: String,
                                 toBackStack: Boolean) {
        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            if (FragmentHome.TAG == tag) {
                fragment = FragmentHome()
            } else if (FragmentDashboard.TAG == tag) {
                fragment = FragmentDashboard()
            } else if (FragmentMessages.TAG == tag) {
                fragment = FragmentMessages()
            }
        }

        if (fragment != null) {
            val ft = supportFragmentManager
                .beginTransaction()
            ft.replace(R.id.fragmentContainer, fragment!!, tag)
            if (toBackStack) {
                //ft.addToBackStack(null)
            }
            ft.commit()
        }
    }

    private val myOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                showFragmentByTag(FragmentHome.TAG, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                showFragmentByTag(FragmentDashboard.TAG, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                showFragmentByTag(FragmentMessages.TAG, true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
