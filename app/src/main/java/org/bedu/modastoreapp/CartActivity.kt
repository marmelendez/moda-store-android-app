package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import me.ibrahimsn.lib.SmoothBottomBar

class CartActivity : AppCompatActivity() {

    private lateinit var bottomBar : SmoothBottomBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val listFragment = supportFragmentManager.findFragmentById(R.id.fragmentList) as ListFragment
        bottomBar = findViewById(R.id.bottomBar)

        listFragment.setListener{
            val detailFragment = supportFragmentManager.findFragmentById(R.id.fragmentDetail) as? DetailFragment

            // Pantalla grande, mostrar detalle en el fragment
            if(detailFragment!=null){
                detailFragment.showProduct(it)
            } else{ //pantalla pequeña, navegar a un nuevo Activity
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.PRODUCT,it)
                startActivity(intent)
            }
        }

        bottomBar.onItemSelected = {
            when (it) {
                /*0 -> {
                    val intent = Intent(this, ShopActivity::class.java)
                    startActivity(intent)
                }*/
                1 -> {
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(this, ConfigurationActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}