package org.bedu.modastoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView;
import org.bedu.modastoreapp.modelos.BaseDatos
import org.bedu.modastoreapp.modelos.Product
import org.bedu.modastoreapp.modelos.Store

class SearchProductActivity : AppCompatActivity() {
    private lateinit var dataList: RecyclerView
    private lateinit var products: MutableList<Product>
    private lateinit var inpSearch: EditText
    val myStore: Store = BaseDatos.start()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        products = myStore.catalogProduct
        dataList = findViewById(R.id.datalist)
        inpSearch = findViewById(R.id.inp_Search)

        setAdapter(products)

        inpSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val result = myStore.searchProduct(inpSearch.text.toString())
                if (!result.isNotEmpty()) {
                    Toast.makeText(applicationContext ,"Ups! no encontramos un producto con ese nombre pero esto es similar;)", Toast.LENGTH_LONG).show()
                }  else {
                    products = result.toMutableList()
                    setAdapter(products)
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setAdapter(products: MutableList<Product>) {
        var adapter = RecyclerAdapter(this, products)
        var gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        dataList.setLayoutManager(gridLayoutManager)
        dataList.setAdapter(adapter)
    }
}