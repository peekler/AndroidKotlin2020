package hu.ecity.menuandfragmentdemo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hu.ecity.menuandfragmentdemo.FragmentDashboard
import hu.ecity.menuandfragmentdemo.FragmentHome
import hu.ecity.menuandfragmentdemo.FragmentMessages

class MyPagerAdapter(fragManager: FragmentManager) : FragmentPagerAdapter(fragManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentHome()
            }
            1 -> {
                FragmentDashboard()
            }
            else -> {
                FragmentMessages()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                "Home"
            }
            1 -> {
                "Dashboard"
            }
            else -> {
                "Messages"
            }
        }
    }

}