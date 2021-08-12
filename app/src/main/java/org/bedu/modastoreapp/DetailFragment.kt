package org.bedu.modastoreapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class DetailFragment : Fragment() {

    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var imgProduct: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        productName = view.findViewById(R.id.nameCProduct)
        productPrice = view.findViewById(R.id.priceCProduct)
        imgProduct = view.findViewById(R.id.imgCProduct)

        return view
    }

    fun showProduct(product: Product){
        view?.visibility = View.VISIBLE
        productName.text = product.name
        productPrice.text = product.price
        imgProduct.setImageResource(product.idImage)
    }

}