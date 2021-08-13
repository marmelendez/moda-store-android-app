package org.bedu.modastoreapp

import Items

import RecyclerAdapterOrder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Orders : AppCompatActivity() {

    private lateinit var OrderAdapter : RecyclerAdapterOrder
    private lateinit var recyclerViewOrders : RecyclerView
    private lateinit var favoriteIcon: ImageView
    private lateinit var HistoryIcon: ImageView
    private lateinit var ToolsButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        favoriteIcon=findViewById(R.id.FavoritesListButton)
        HistoryIcon=findViewById(R.id.HistoryListButton)
        ToolsButton=findViewById(R.id.ToolsButton)

        fun getItems(): MutableList<Items>{
            val items:MutableList<Items> = ArrayList()

            items.add(Items("1234", "$950.00","3",
                R.drawable.white2_shirt,R.drawable.gray_casual_shirt,R.drawable.sport_shirt))
            items.add(Items("6789", "$1750.00", "4",
            R.drawable.black_cotton_shirt,R.drawable.black_shirt,R.drawable.blac_red_shirt))
            items.add(Items("4532", "$1250.00", "5",
                R.drawable.light_blue_shirt,R.drawable.pink_shirt,R.drawable.polo_shirt))
            return items
        }

        recyclerViewOrders=findViewById(R.id.ListOrders)

        // indicamos que tiene un tamaño fijo
        recyclerViewOrders.setHasFixedSize(true)
        // indicamos el tipo de layoutManager
        recyclerViewOrders.layoutManager = LinearLayoutManager(this)
        //seteando el Adapter
        OrderAdapter = RecyclerAdapterOrder(getItems(),this)
        //asignando el Adapter al RecyclerView
        recyclerViewOrders.adapter = OrderAdapter

    }
}
