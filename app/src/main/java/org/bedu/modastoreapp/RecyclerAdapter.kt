package org.bedu.modastoreapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.bedu.modastoreapp.modelos.Product

class RecyclerAdapter(
    private val context: Context,
    private val products: MutableList<org.bedu.modastoreapp.modelos.Product>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.grid_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products.get(position)
        holder.bind(product, context)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val productName = view.findViewById(R.id.nameProduct) as TextView
        val price = view.findViewById(R.id.priceProduct) as TextView
        val image = view.findViewById(R.id.imgProduct) as ImageView

        fun bind(product: Product, context: Context){
            productName.text = product.name
            price.text = product.price.toString()
            image.setImageResource(product.image)

            itemView.setOnClickListener { v ->
                Toast.makeText( v.context, "CLICKK", Toast.LENGTH_SHORT).show()
            }

        }
    }
}