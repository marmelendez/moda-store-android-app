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
    private val products: MutableList<Product>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.grid_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products.get(position)
        holder.bind(product, context)
        //holder.view.setOnClickListener{clickListener(product)}
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //obteniendo las referencias a las Views
        val productName = view.findViewById(R.id.nameProduct) as TextView
        //val description = view.findViewById(R.id.tvDescription) as TextView
        val price = view.findViewById(R.id.priceProduct) as TextView
        val image = view.findViewById(R.id.imgProduct) as ImageView

        //"atando" los datos a las Views
        fun bind(product: Product, context: Context){
            productName.text = product.name
            //description.text = product.description
            price.text = product.price.toString()
            image.setImageResource(product.image)

            itemView.setOnClickListener { v ->
                Toast.makeText( v.context, "CLICKK", Toast.LENGTH_SHORT).show()
            }

        }
    }
}