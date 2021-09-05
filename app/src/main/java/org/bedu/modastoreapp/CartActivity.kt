package org.bedu.modastoreapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.ibrahimsn.lib.SmoothBottomBar
import org.bedu.modastoreapp.listas.DetailFragment
import org.bedu.modastoreapp.listas.ListFragment
import org.bedu.modastoreapp.modelos.Order
import org.bedu.modastoreapp.modelos.Product
import org.bedu.modastoreapp.modelos.RegisteredUser

class CartActivity : AppCompatActivity() {

    private lateinit var menu_bar : BottomNavigationView
    private lateinit var button_pay : Button
    private lateinit var total_price : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        menu_bar = findViewById(R.id.cart_menu)
        button_pay = findViewById(R.id.cart_btn_shop)
        total_price = findViewById(R.id.cart_txt_total)

        val username = "tomas11"
        val regUser = MYSTORE.getUserName(username)

        if (regUser != null) {
            total_price.text = "$ ${regUser.getTotal()}"
        }

        button_pay.setOnClickListener{
            if (regUser != null) {
                confirmDialog(regUser)
            }
        }


        val listFragment = supportFragmentManager.findFragmentById(R.id.cart_fragment_container) as ListFragment
        if (username != null) {
            listFragment.setUsername(username)
        } else {
            listFragment.setUsername("tomas11")
        }

        listFragment.setListener{
            val detailFragment = supportFragmentManager.findFragmentById(R.id.fragmentDetail) as? DetailFragment

            if(detailFragment!=null){
                detailFragment.showProduct(it)
            } else{
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.PRODUCT,it)
                startActivity(intent)
            }
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
                R.id.menu_profile -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, username)

                    val intent = Intent(this, ProfileActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)
                }
            }
            true
        }
    }

    private fun confirmDialog(regUser: RegisteredUser) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Pago")
        builder.setMessage("¿Deseas pagar un total de ${regUser.getTotal()} por tu compra?")

        builder.setNegativeButton(
            "Cancelar"
        ) { dialog, which -> }

        builder.setPositiveButton(
            "ok"
        ) { dialogInterface, i ->
            val order = Order(regUser.getOrders().size,regUser.getShoppingCart(),regUser.getTotal(),regUser.getAddress())
            regUser.addOrder(order)
            regUser.setShoppingCart(mutableListOf<Product>())
            Toast.makeText(applicationContext, "Gracias por tu compra ID: ${order.id}", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}