package br.com.aneteles.navigatedrawer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MenuActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Você clicou aqui!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            sendEmail("tawanesilva.96@hotmail.com","app",getString(R.string.app_text_about))
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passar cada ID de menu como um conjunto de IDs porque cada
        // o menu deve ser considerado como destino de nível superior.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_profile, R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Infle o menu; isso adiciona itens à barra de ação, se estiver presente.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun sendEmail(recipient: String, subject: String, message: String) {
        /*ACTION_SEND ação para lançar um cliente de e-mail instalado em seu dispositivo Android.*/
        val mIntent = Intent(Intent.ACTION_SEND)
        /*Para enviar um e-mail, você precisa especificar mailto: como URI usando o método setData ()
        e o tipo de dados será text / plain usando o método setType ()*/
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        // colocar o e-mail do destinatário na intenção
        /* o destinatário é colocado como array porque você pode querer enviar e-mail para vários e-mails
           então digite e-mails separados por vírgula (,), eles serão armazenados na matriz*/
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        //coloque o assunto na intenção
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        //coloque a mensagem na intenção
        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            //iniciar intenção de e-mail
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            //se alguma coisa der errado, por exemplo, nenhum aplicativo cliente de e-mail ou qualquer exceção
            //obter e mostrar mensagem de exceção
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}