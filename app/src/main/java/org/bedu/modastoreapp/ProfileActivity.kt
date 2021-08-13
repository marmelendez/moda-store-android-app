package org.bedu.modastoreapp

import Product
import RecyclerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ProfileActivity : AppCompatActivity() {

    private lateinit var Adapter : RecyclerAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var favoriteIcon: ImageView
    private lateinit var HistoryIcon: ImageView
    private lateinit var ToolsButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        favoriteIcon=findViewById(R.id.FavoritesListButton)
        HistoryIcon=findViewById(R.id.HistoryListButton)
        ToolsButton=findViewById(R.id.ToolsButton)

        HistoryIcon.setOnClickListener(){
            navigateToOrders()
        }

        fun getProducts(): MutableList<Product>{
            val products:MutableList<Product> = ArrayList()

            products.add(Product("Black shirt", "$550.00", R.drawable.shirt))
            products.add(Product("Purple shirt", "$750.00", R.drawable.shirt))
            products.add(Product("White shirt", "$250.00", R.drawable.shirt))
            products.add(Product("Yellow shirt", "$350.00", R.drawable.shirt))
            products.add(Product("Orange shirt", "$150.00", R.drawable.shirt))
            return products
        }

        recyclerView=findViewById(R.id.List)

        // indicamos que tiene un tamaño fijo
        recyclerView.setHasFixedSize(true)
        // indicamos el tipo de layoutManager
        recyclerView.layoutManager = GridLayoutManager(this,2)
        //seteando el Adapter
        Adapter = RecyclerAdapter(getProducts(),this)
        //asignando el Adapter al RecyclerView
        recyclerView.adapter = Adapter

    }

    private fun navigateToOrders(){
        val intent = Intent(this,Orders::class.java)
        startActivity(intent)
    }


}

