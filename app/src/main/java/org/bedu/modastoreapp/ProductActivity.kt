package org.bedu.modastoreapp

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

        btnReturn = findViewById(R.id.product_btn_return)
        btnAddCart = findViewById(R.id.product_btn_addcart)
        btnAddFav = findViewById(R.id.product_btn_addfavorites)
        imgProduct = findViewById(R.id.product_img)
        txtProductName = findViewById(R.id.product_name)
        txtProductPrice = findViewById(R.id.product_price)

        /*val bundle = intent.extras
        val productId = bundle?.getInt(PRODUCTID)
        val product = STORE.getProduct(productId)
        val userName = bundle?.getString(USERNAME)*/

        btnReturn.setOnClickListener {
            finish()
        }

        /*if (product!=null) {
            imgProduct.setImageResource(product.image)
            txtProductName.text = product.name
            txtProductPrice.text = "$ ${product.price}"
        }*/

        btnAddFav.setOnClickListener {
            Toast.makeText(applicationContext ,"El producto ha sido añadido a tus favoritos", Toast.LENGTH_LONG).show()
            /*if (userName != null && userName != "" && product != null) {
                STORE.getUserName(userName)?.addToFavorite(product)
                btnAddFav.setImageResource(R.drawable.ic_productfav)
                Toast.makeText(applicationContext ,"El producto ha sido añadido a tus favoritos", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext ,"Inicia sesión", Toast.LENGTH_LONG).show()
            }*/
        }

        btnAddCart.setOnClickListener {
            Toast.makeText(applicationContext ,"El producto ha sido añadido a tu carrito", Toast.LENGTH_LONG).show()
            /*if (userName != null && userName != "" && product != null) {
                STORE.getUserName(userName)?.addToCart(product)
                Toast.makeText(applicationContext ,"El producto ha sido añadido a tu carrito", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext ,"Inicia sesión", Toast.LENGTH_LONG).show()
            }*/
        }
    }
}