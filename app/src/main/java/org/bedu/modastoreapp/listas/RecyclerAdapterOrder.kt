package org.bedu.modastoreapp.listas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.bedu.modastoreapp.*
import org.bedu.modastoreapp.modelos.Order

class RecyclerAdapterOrder (
    private val context: Context,
    private val orders: MutableList<Order>) : RecyclerView.Adapter<RecyclerAdapterOrder.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_order, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders.get(position)
        holder.bind(order)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val id = view.findViewById(R.id.idCompra) as TextView
        val total = view.findViewById(R.id.OrderPrice) as TextView
        val cant = view.findViewById(R.id.orderProducts) as TextView

        fun bind(order: Order) {
            id.text = order.id.toString()
            total.text = order.total.toString()
            cant.text = order.products.size.toString()
        }
    }
}