package br.com.aneteles.navigatedrawer.ui.profile

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import br.com.aneteles.navigatedrawer.R

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        val bt_face = root.findViewById<ImageButton>(R.id.img_button_face)
        val bt_insta = root.findViewById<ImageButton>(R.id.img_button_insta)
        val bt_linkedin = root.findViewById<ImageButton>(R.id.img_button_linkedin)
        val bt_github = root.findViewById<ImageButton>(R.id.img_button_github)
        val bt_about = root.findViewById<Button>(R.id.button_about)

        bt_face.setOnClickListener { navigateToFacebook() }
        bt_insta.setOnClickListener { navigateToInstagram() }
        bt_linkedin.setOnClickListener { navigateToLinkedin() }
        bt_github.setOnClickListener { navigateToGitHub() }
        bt_about.setOnClickListener { navigateToAbout() }

        return root
    }

    private fun navigateToAbout() {
        // Esse NavHost e para navegar entre os fragment quando usa o navigation
        NavHostFragment.findNavController(this).navigate(R.id.nav_about)
    }

    private fun navigateToFacebook() {
        openNetwork(
            "com.facebook.katana",
            "fb://facewebmodal/f?href=https://www.facebook.com/tawane.silvabarbosa",
            "https://www.facebook.com/tawane.silvabarbosa/"
        )
    }

    private fun navigateToInstagram() {
        openNetwork(
            "com.instagram.android",
            "http://instagram.com/_u/aneteles",
            "http://instagram.com/aneteles"
        )
    }

    private fun navigateToLinkedin() {
        openNetwork(
            "com.linkedin.android",
            "https://www.linkedin.com/in/tawane-silva-6660b58a/",
            "https://www.linkedin.com/in/tawane-silva-6660b58a/"
        )
    }

    private fun navigateToGitHub() {
        openNetwork(
            "", "", "https://github.com/Tawane-Teles"
        )
    }

    private fun openNetwork(
        appPackage: String,
        appAddress: String,
        webAddress: String
    ) {
        val uri = Uri.parse(appAddress)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        intent.setPackage(appPackage)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            /*
             * Se não houver o aplicativo da rede
             * social acionada, então abra a página
             * no navegador padrão do aparelho, Web.
             * */
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(webAddress)
                )
            )
        }
    }
}