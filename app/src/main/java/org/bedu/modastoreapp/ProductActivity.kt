package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ProductActivity : AppCompatActivity() {

    private lateinit var btnReturn : Button
    private lateinit var btnAddCart : Button
    private lateinit var btnAddFav : ImageView
    private lateinit var imgProduct : ImageView
    private lateinit var txtProductName : TextView
    private lateinit var txtProductPrice : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        btnReturn = findViewById(R.id.btn_Return)
        btnAddCart = findViewById(R.id.btn_addcart)
        btnAddFav = findViewById(R.id.btn_addfav)
        imgProduct = findViewById(R.id.productImage)
        txtProductName = findViewById(R.id.productName)
        txtProductPrice = findViewById(R.id.productPrice)

        val bundle = intent.extras
        val productId = bundle?.getInt(PRODUCTID)
        val product = MYSTORE.getProduct(productId)
        val userName = bundle?.getString(USERNAME)

        btnReturn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, userName)

            val intent = Intent(this, SearchProductActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }


        if (product!=null) {
            imgProduct.setImageResource(product.image)
            txtProductName.text = product.name
            txtProductPrice.text = "$ ${product.price}"
        }

        btnAddFav.setOnClickListener {
            if (userName != null && userName != "" && product != null) {
                MYSTORE.getUserName(userName)?.addToFavorite(product)
                btnAddFav.setImageResource(R.drawable.ic_productfav)
                Toast.makeText(applicationContext ,"El producto ha sido añadido a tus favoritos", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext ,"Inicia sesión", Toast.LENGTH_LONG).show()
            }
        }

        btnAddCart.setOnClickListener {
            if (userName != null && userName != "" && product != null) {
                MYSTORE.getUserName(userName)?.addToCart(product)
                Toast.makeText(applicationContext ,"El producto ha sido añadido a tu carrito", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext ,"Inicia sesión", Toast.LENGTH_LONG).show()
            }
        }
    }
}