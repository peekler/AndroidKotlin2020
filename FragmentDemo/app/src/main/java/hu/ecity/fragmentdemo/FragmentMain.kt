package hu.ecity.fragmentdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.view.*

class FragmentMain : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main,container,false)

        rootView.btnPress.setOnClickListener {
            Toast.makeText(activity, "Pressed", Toast.LENGTH_LONG).show()
        }


        return rootView
    }

}