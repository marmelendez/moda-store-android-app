package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.bedu.modastoreapp.listas.RecyclerAdapter
import org.bedu.modastoreapp.modelos.Product

class SearchProductActivity : AppCompatActivity() {

    private lateinit var items_list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        var products = MYSTORE.catalogProduct
        items_list = findViewById(R.id.search_datalist)
        val input_search = findViewById<EditText>(R.id.search_search)
        val menu_bar = findViewById<BottomNavigationView>(R.id.search_menu)

        val bundle = intent.extras
        val username = bundle?.getString(USERNAME)

        if (username != null) {
            setAdapter(products, username)
        } else {
            setAdapter(products, "")
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

        input_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val result = MYSTORE.searchProduct(input_search.text.toString())
                if (result.isNotEmpty()) {
                    products = result.toMutableList()
                    if (username != null) {
                        setAdapter(products,username)
                    } else {
                        setAdapter(products,"")
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }


    private fun setAdapter(products: MutableList<Product>, userName: String) {
        var adapter = RecyclerAdapter(this, products, userName)
        var gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        items_list.setLayoutManager(gridLayoutManager)
        items_list.setAdapter(adapter)
    }
}