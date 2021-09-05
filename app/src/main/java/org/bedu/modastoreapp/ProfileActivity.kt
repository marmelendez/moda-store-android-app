package org.bedu.modastoreapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.bedu.modastoreapp.listas.RecyclerAdapter
import org.bedu.modastoreapp.modelos.Product

class ProfileActivity : AppCompatActivity() {

    private lateinit var Adapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteIcon: ImageView
    private lateinit var orderIcon: ImageView
    private lateinit var btn_config: ImageButton
    private lateinit var products: MutableList<Product>
    private lateinit var menu_bar : BottomNavigationView
    private lateinit var title: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        menu_bar = findViewById(R.id.profile_menu)

        menu_bar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_shopping_cart -> {
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    fun showPopup(v: View) {
        val popupMenu = PopupMenu(this, v)

        popupMenu.menuInflater.inflate(R.menu.configuration_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_data -> {
                    val intent = Intent(this, ConfigAccountDataActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_address -> {
                    val intent = Intent(this, ConfigAddressActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_payment -> {
                    val intent = Intent(this, ConfigPaymentActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_logout -> {
                    confirmDialog()
                }
            }
            true
        }
        popupMenu.show()
    }

    private fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Cerrar sesión")
        builder.setMessage("¿Estas segurx que quieres cerrar sesión?")

        builder.setNegativeButton(
            "No"
        ) { dialog, which -> }

        builder.setPositiveButton(
            "Si"
        ) { dialogInterface, i ->
            Firebase.auth.signOut()
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

/*
        favoriteIcon=findViewById(R.id.FavoritesListButton)
        orderIcon=findViewById(R.id.HistoryListButton)
        configIcon=findViewById(R.id.configButton)
        bottomBar = findViewById(R.id.bottomBarProfile)
        title = findViewById(R.id.configUser)
        recyclerView=findViewById(R.id.favoriteslist)

        val bundle = intent.extras
        var userName = bundle?.getString(USERNAME)

        orderIcon.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, userName)

            val intent = Intent(this, OrderActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }

        configIcon.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, userName)

            val intent = Intent(this, ConfigurationActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }

        /*bottomBar.onItemReselected = {
            evalCase(it, userName)
        }

        bottomBar.onItemSelected = {
            evalCase(it, userName)
        }*/

        if (userName != null) {
            val regUser = MYSTORE.getUserName(userName)
            title.text = "¡Hola ${userName}!"
            if (regUser != null) {
                products = regUser.getFavorites()
            } else {
                products = mutableListOf()
            }
        } else {
            userName = ""
            products = mutableListOf()
        }

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        Adapter = RecyclerAdapter(this, products, userName)
        recyclerView.adapter = Adapter

    }

    /*private fun evalCase(it: Int, userName: String?) {
        when (it) {
            0 -> {
                val bundle = Bundle()
                bundle.putString(USERNAME, userName)

                val intent = Intent(this, ShopActivity::class.java).apply {
                    putExtras(bundle)
                }

                startActivity(intent)
            }1 -> {
            val bundle = Bundle()
            bundle.putString(USERNAME, userName)

            val intent = Intent(this, CartActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }2 -> {
            val bundle = Bundle()
            bundle.putString(USERNAME, userName)

            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }
        }

    }*/
}

 */
}
