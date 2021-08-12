package org.bedu.modastoreapp.listas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.bedu.modastoreapp.R
import org.bedu.modastoreapp.modelos.Product

class RecyclerAdapterShop (
    private val context:Context,
    private val products: MutableList<Product>): RecyclerView.Adapter<RecyclerAdapterShop.ViewHolder>() { //private val clickListener: (Product) -> Unit

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products.get(position)
        holder.bind(product, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_contact, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val productName = view.findViewById(R.id.itemName) as TextView
        val price = view.findViewById(R.id.itemPrice) as TextView
        val image = view.findViewById(R.id.itemImage) as ImageView

        fun bind(product: Product, context: Context){
            productName.text = product.name
            price.text = product.price.toString()
            image.setImageResource(product.image)
        }
    }
}