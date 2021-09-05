package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var bottomBar : SmoothBottomBar
    private lateinit var title : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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

        bottomBar.onItemReselected = {
            evalCase(it, userName)
        }

        bottomBar.onItemSelected = {
            evalCase(it, userName)
        }

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

    private fun evalCase(it: Int, userName: String?) {
        when (it) {
            0 -> {
                val bundle = Bundle()
                bundle.putString(USERNAME, userName)

                val intent = Intent(this, HomeActivity::class.java).apply {
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

    }
}

