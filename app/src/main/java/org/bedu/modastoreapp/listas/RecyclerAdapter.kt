package org.bedu.modastoreapp.listas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import org.bedu.modastoreapp.PRODUCTID
import org.bedu.modastoreapp.ProductActivity
import org.bedu.modastoreapp.R
import org.bedu.modastoreapp.USERNAME
import org.bedu.modastoreapp.modelos.Product
import org.bedu.modastoreapp.modelos.RegisteredUser

class RecyclerAdapter(
    private val context: Context,
    private val products: MutableList<Product>,
    private val username: String) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.grid_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products.get(position)
        holder.bind(product,username, context)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val productName = view.findViewById(R.id.nameProduct) as TextView
        val price = view.findViewById(R.id.priceProduct) as TextView
        val image = view.findViewById(R.id.imgProduct) as ImageView

        fun bind(product: Product, username: String, context: Context){
            productName.text = product.name
            price.text = product.price.toString()
            image.setImageResource(product.image)

            itemView.setOnClickListener { v ->
                val bundle = Bundle()
                bundle.putInt(PRODUCTID, product.idProduct)
                bundle.putString(USERNAME, username)

                val intent = Intent(v.context, ProductActivity::class.java).apply {
                    putExtras(bundle)
                }

                v.context.startActivity(intent)
            }
        }
    }
}