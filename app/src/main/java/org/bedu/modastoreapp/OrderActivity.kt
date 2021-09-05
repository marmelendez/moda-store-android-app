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
import org.bedu.modastoreapp.listas.RecyclerAdapterOrder
import org.bedu.modastoreapp.modelos.Order

class OrderActivity : AppCompatActivity() {

    /*private lateinit var Adapter : RecyclerAdapterOrder
    private lateinit var recyclerView : RecyclerView
    private lateinit var favoriteIcon: ImageView
    private lateinit var orderIcon: ImageView
    private lateinit var ToolsButton: ImageButton
    private lateinit var orders: MutableList<Order>
    private lateinit var bottomBar : SmoothBottomBar
    private lateinit var title : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        favoriteIcon=findViewById(R.id.FavoritesListButtonOrder)
        orderIcon=findViewById(R.id.HistoryListButtonOrder)
        ToolsButton=findViewById(R.id.configButtonOrder)
        bottomBar = findViewById(R.id.bottomBarOrders)
        title = findViewById(R.id.configUserOrder)

        val bundle = intent.extras
        var userName = bundle?.getString(USERNAME)

        ToolsButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, userName)

            val intent = Intent(this, ConfigurationActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }

        favoriteIcon.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, userName)

            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }

        bottomBar.onItemSelected = {
            when (it) {
                0 -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, userName)

                    val intent = Intent(this, HomeActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)
                }
                1 -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, userName)

                    val intent = Intent(this, CartActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)
                }
                2 -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, userName)

                    val intent = Intent(this, ProfileActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)
                }
            }
        }

        if (userName != null) {
            val regUser = STORE.getUserName(userName)
            title.text = "¡Hola ${userName}!"
            if (regUser != null) {
                orders = regUser.getOrders()
            } else {
                orders = mutableListOf()
            }
        } else {
            userName = ""
            orders = mutableListOf()
        }

        recyclerView=findViewById(R.id.orderlist)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this,1)
        Adapter = RecyclerAdapterOrder(this, orders)
        recyclerView.adapter = Adapter

    }*/
}
