package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView;
import me.ibrahimsn.lib.SmoothBottomBar
import org.bedu.modastoreapp.listas.RecyclerAdapter
import org.bedu.modastoreapp.modelos.BaseDatos
import org.bedu.modastoreapp.modelos.Product
import org.bedu.modastoreapp.modelos.Store

class SearchProductActivity : AppCompatActivity() {
    private lateinit var dataList: RecyclerView
    private lateinit var products: MutableList<Product>
    private lateinit var inpSearch: EditText
    private lateinit var bottomBar : SmoothBottomBar
    private var listener : (Product) ->Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        products = MYSTORE.catalogProduct
        dataList = findViewById(R.id.datalist)
        inpSearch = findViewById(R.id.inp_Search)
        bottomBar = findViewById(R.id.bottomBarSearch)

        val bundle = intent.extras
        val userName = bundle?.getString(USERNAME)

        if (userName != null) {
            setAdapter(products, userName)
        } else {
            setAdapter(products, "")
        }

        bottomBar.onItemSelected = {
            when (it) {
                0 -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, userName)

                    val intent = Intent(this, ShopActivity::class.java).apply {
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

                    val intent = Intent(this, ConfigurationActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)
                }
            }
        }

        inpSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val result = MYSTORE.searchProduct(inpSearch.text.toString())
                if (!result.isNotEmpty()) {
                    Toast.makeText(applicationContext ,"Ups! no encontramos un producto con ese nombre pero esto es similar;)", Toast.LENGTH_LONG).show()
                }  else {
                    products = result.toMutableList()
                    if (userName != null) {
                        setAdapter(products,userName)
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
        dataList.setLayoutManager(gridLayoutManager)
        dataList.setAdapter(adapter)
    }
}