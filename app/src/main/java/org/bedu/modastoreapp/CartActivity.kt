package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import me.ibrahimsn.lib.SmoothBottomBar
import org.bedu.modastoreapp.listas.DetailFragment
import org.bedu.modastoreapp.listas.ListFragment

class CartActivity : AppCompatActivity() {

    private lateinit var bottomBar : SmoothBottomBar
    private lateinit var button : Button
    private lateinit var totalPrice : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        bottomBar = findViewById(R.id.bottomBar)
        button = findViewById(R.id.next_button)
        totalPrice = findViewById(R.id.priceTotal)

        val bundle = intent.extras
        val userName = bundle?.getString(USERNAME)

        val regUser = MYSTORE.getUserName(userName.toString())
        if (regUser != null) {
            totalPrice.text = "$ ${regUser.getTotal().toString()}"
        }

        val listFragment = supportFragmentManager.findFragmentById(R.id.fragmentList) as ListFragment
        if (userName != null) {
            listFragment.setUsername(userName)
        } else {
            listFragment.setUsername("tomas11")
        }

        button.setOnClickListener{
            Toast.makeText(applicationContext, "Pronto contaremos con esta función", Toast.LENGTH_SHORT).show()
        }


        listFragment.setListener{
            val detailFragment = supportFragmentManager.findFragmentById(R.id.fragmentDetail) as? DetailFragment

            // Pantalla grande, mostrar detalle en el fragment
            if(detailFragment!=null){
                detailFragment.showProduct(it)
            } else{ //pantalla pequeña, navegar a un nuevo Activity

                //MOSTRAR PANTALLA DE PRODUCTO
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.PRODUCT,it)
                startActivity(intent)
            }
        }
        bottomBar.onItemReselected = {
            evalCase(it, userName)
        }


        bottomBar.onItemSelected = {
            evalCase(it, userName)
        }
    }

    private fun evalCase(it: Int, userName: String?) {
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

            val intent = Intent(this, ConfigurationActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
            }
        }

    }
}