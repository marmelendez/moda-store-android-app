package org.bedu.modastoreapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.flaviofaria.kenburnsview.KenBurnsView
import com.squareup.picasso.Picasso
import org.bedu.modastoreapp.ShopContainerAdapter.TravelLocationViewHolder

class ShopContainerAdapter(private val shopContainers: MutableList<ShopContainer>) :
    RecyclerView.Adapter<TravelLocationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelLocationViewHolder {
        return TravelLocationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_container_location,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TravelLocationViewHolder, position: Int) {
        holder.setLocationData(shopContainers[position])
    }

    override fun getItemCount(): Int {
        return shopContainers.size
    }

    class TravelLocationViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val kbvLocation: KenBurnsView
        private val textTitle: TextView
        private val textStarRating: TextView
        fun setLocationData(shopContainer: ShopContainer) {
            shopContainer.image?.let { Picasso.get().load(it).into(kbvLocation) }
            textTitle.text = shopContainer.title
            //textStarRating.text = shopContainer.starRating.toString()
        }

        init {
            kbvLocation = itemView.findViewById(R.id.kbvLocation)
            textTitle = itemView.findViewById(R.id.textTitle)
            textStarRating = itemView.findViewById(R.id.textStarRating)
        }
    }
}