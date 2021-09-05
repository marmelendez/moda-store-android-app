package org.bedu.modastoreapp

import android.animation.Animator
import android.animation.AnimatorInflater
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val btnReturn = findViewById<Button>(R.id.btn_Return)
        val btnAddCart = findViewById<Button>(R.id.btn_addcart)
        val btnAddFav = findViewById<ImageView>(R.id.btn_addfav)
        val imgProduct = findViewById<ImageView>(R.id.productImage)
        val txtProductName = findViewById<TextView>(R.id.productName)
        val txtProductPrice = findViewById<TextView>(R.id.productPrice)

        val bundle = intent.extras
        val productId = bundle?.getInt(PRODUCTID)
        val product = MYSTORE.getProduct(productId)
        val userName = bundle?.getString(USERNAME)

        btnReturn.setOnClickListener {
            finish()
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
                blink(btnAddFav)
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

    private fun blink(btnAddFav : ImageView) {
        AnimatorInflater.loadAnimator(this, R.animator.blinking).apply {
            setTarget(btnAddFav)
            start()
        }
    }
}
