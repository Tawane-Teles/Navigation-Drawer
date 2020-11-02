package br.com.aneteles.navigatedrawer.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import br.com.aneteles.navigatedrawer.R

class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
            ViewModelProviders.of(this).get(AboutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_about, container, false)

        val img_close = root.findViewById<ImageView>(R.id.img_closet)
        img_close.setOnClickListener { navigateToProfile() }

        return root
    }

    private fun navigateToProfile () {
        NavHostFragment.findNavController(this).navigate(R.id.nav_profile)
    }
}