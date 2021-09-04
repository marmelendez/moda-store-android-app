package org.bedu.modastoreapp.listas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.bedu.modastoreapp.*
import org.bedu.modastoreapp.modelos.Product

class RecyclerAdapterShop (
    private val context:Context,
    private val products: MutableList<Product>,
    private val username: String = ""): RecyclerView.Adapter<RecyclerAdapterShop.ViewHolder>() { //private val clickListener: (Product) -> Unit

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products.get(position)
        holder.bind(product, username, context)
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
        val deleteIcon = view.findViewById(R.id.delete_icon) as ImageView

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

            deleteIcon.setOnClickListener{
                if (username != null && username != "" && product != null) {
                    STORE.getUserName(username)?.removeFromCart(product)
                    Toast.makeText(it.context ,"El producto ha sido eliminado de tu carrito", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(it.context ,"Inicia sesión", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}