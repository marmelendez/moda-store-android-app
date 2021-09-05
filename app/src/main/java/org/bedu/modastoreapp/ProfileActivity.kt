package org.bedu.modastoreapp

import android.animation.AnimatorInflater
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import me.ibrahimsn.lib.SmoothBottomBar
import org.bedu.modastoreapp.listas.RecyclerAdapter
import org.bedu.modastoreapp.modelos.Product


class ProfileActivity : AppCompatActivity() {

    private lateinit var Adapter : RecyclerAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var favoriteIcon: ImageView
    private lateinit var orderIcon: ImageView
    private lateinit var configIcon: ImageButton
    private lateinit var products: MutableList<Product>
    private lateinit var menu_bar : BottomNavigationView
    private lateinit var username : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        favoriteIcon=findViewById(R.id.profile_img_favorite)
        orderIcon=findViewById(R.id.profile_img_history)
        configIcon=findViewById(R.id.profile_btn_config)
        menu_bar = findViewById(R.id.profile_menu)
        recyclerView=findViewById(R.id.favoriteslist)

        val bundle = intent.extras
        username = bundle?.getString(USERNAME).toString()

        blink(favoriteIcon)

        orderIcon.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, username)

            val intent = Intent(this, OrderActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }

        menu_bar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, username)

                    val intent = Intent(this, HomeActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)
                }
                R.id.menu_shopping_cart -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, username)

                    val intent = Intent(this, CartActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)
                }
            }
            true
        }


        if (username != null) {
            val regUser = MYSTORE.getUserName(username)
            if (regUser != null) {
                products = regUser.getFavorites()
            } else {
                products = mutableListOf()
            }
        } else {
            username = ""
            products = mutableListOf()
        }

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        Adapter = RecyclerAdapter(this, products, username)
        recyclerView.adapter = Adapter

    }

    private fun blink(btnAddFav : ImageView) {
        AnimatorInflater.loadAnimator(this, R.animator.blinking).apply {
            setTarget(btnAddFav)
            start()
        }
    }

    fun showPopup(v: View) {
        val popupMenu = PopupMenu(this, v)

        popupMenu.menuInflater.inflate(R.menu.configuration_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_data -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, username)

                    val intent = Intent(this, ConfigAccountDataActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)
                }
                R.id.menu_address -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, username)

                    val intent = Intent(this, ConfigAddressActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)
                }
                R.id.menu_payment -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, username)

                    val intent = Intent(this, ConfigPaymentActivity::class.java).apply {
                        putExtras(bundle)
                    }

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
}

