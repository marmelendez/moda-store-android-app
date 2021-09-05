package org.bedu.modastoreapp

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

        val btn_return = findViewById<Button>(R.id.product_btn_return)
        val btn_add_cart = findViewById<Button>(R.id.product_btn_add_cart)
        val btn_add_fav = findViewById<ImageView>(R.id.product_btn_add_fav)
        val product_img = findViewById<ImageView>(R.id.product_img)
        val product_name = findViewById<TextView>(R.id.product_name)
        val product_price = findViewById<TextView>(R.id.product_price)

        val bundle = intent.extras
        val productId = bundle?.getInt(PRODUCTID)
        val product = MYSTORE.getProduct(productId)
        val userName = bundle?.getString(USERNAME)

        btn_return.setOnClickListener {
            finish()
        }

        if (product!=null) {
            product_img.setImageResource(product.image)
            product_name.text = product.name
            product_price.text = "$ ${product.price}"
        }

        btn_add_fav.setOnClickListener {
            if (userName != null && userName != "" && product != null) {
                MYSTORE.getUserName(userName)?.addToFavorite(product)
                btn_add_fav.setImageResource(R.drawable.ic_productfav)
                blink(btn_add_fav)
                Toast.makeText(applicationContext ,"El producto ha sido añadido a tus favoritos", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext ,"Inicia sesión", Toast.LENGTH_LONG).show()
            }
        }

        btn_add_cart.setOnClickListener {
            if (userName != null && userName != "" && product != null) {
                MYSTORE.getUserName(userName)?.addToCart(product)
                shrink(btn_add_cart)
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

    private fun shrink(button: Button) {
        AnimatorInflater.loadAnimator(this, R.animator.shrink).apply {
            setTarget(button)
            start()
        }
    }
}
