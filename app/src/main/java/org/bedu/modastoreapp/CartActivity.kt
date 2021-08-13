package org.bedu.modastoreapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import me.ibrahimsn.lib.SmoothBottomBar
import org.bedu.modastoreapp.listas.DetailFragment
import org.bedu.modastoreapp.listas.ListFragment
import org.bedu.modastoreapp.modelos.Order
import org.bedu.modastoreapp.modelos.Product
import org.bedu.modastoreapp.modelos.RegisteredUser

class CartActivity : AppCompatActivity() {

    private lateinit var bottomBar : SmoothBottomBar
    private lateinit var payButton : Button
    private lateinit var totalPrice : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        bottomBar = findViewById(R.id.bottomBar)
        payButton = findViewById(R.id.next_button)
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

        payButton.setOnClickListener{
            if (regUser != null) {
                buttonShowDialog_onClick(regUser)
            } else {
                Toast.makeText(applicationContext, "Inicia sesión", Toast.LENGTH_SHORT).show()
            }
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

        bottomBar.onItemReselected = {
            evalCase(it, userName)
        }

        bottomBar.onItemSelected = {
            evalCase(it, userName)
        }
    }

    private fun buttonShowDialog_onClick(regUser: RegisteredUser) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Pago")
        builder.setMessage("¿Deseas pagar un total de ${regUser.getTotal()} por tu compra?")

        builder.setNegativeButton(
            "Cancelar"
        ) { dialog, which -> }

        builder.setPositiveButton(
            R.string.ok
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

                val intent = Intent(this, ProfileActivity::class.java).apply {
                    putExtras(bundle)
                }

                startActivity(intent)
            }
        }

    }
}