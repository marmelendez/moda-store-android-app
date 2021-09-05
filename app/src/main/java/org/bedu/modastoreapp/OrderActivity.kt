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
import org.bedu.modastoreapp.listas.RecyclerAdapterOrder
import org.bedu.modastoreapp.modelos.Order

class OrderActivity : AppCompatActivity() {

    private lateinit var Adapter : RecyclerAdapterOrder
    private lateinit var recyclerView : RecyclerView
    private lateinit var favoriteIcon: ImageView
    private lateinit var orderIcon: ImageView
    private lateinit var orders: MutableList<Order>
    private lateinit var menu_bar : BottomNavigationView
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        favoriteIcon=findViewById(R.id.order_img_favorite)
        orderIcon=findViewById(R.id.order_img_history)
        menu_bar = findViewById(R.id.order_menu)

        val bundle = intent.extras
        username = bundle?.getString(USERNAME).toString()

        blink(orderIcon)


        favoriteIcon.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, username)

            val intent = Intent(this, ProfileActivity::class.java).apply {
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
                orders = regUser.getOrders()
            } else {
                orders = mutableListOf()
            }
        } else {
            username = ""
            orders = mutableListOf()
        }

        recyclerView=findViewById(R.id.orderlist)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this,1)
        Adapter = RecyclerAdapterOrder(this, orders)
        recyclerView.adapter = Adapter

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

    private fun blink(btnAddFav : ImageView) {
        AnimatorInflater.loadAnimator(this, R.animator.blinking).apply {
            setTarget(btnAddFav)
            start()
        }
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
