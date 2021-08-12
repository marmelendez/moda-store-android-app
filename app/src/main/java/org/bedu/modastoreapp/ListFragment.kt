package org.bedu.modastoreapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var mAdapter : RecyclerAdapter
    private var listener : (Product) ->Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerView()
    }

    fun setListener(l: (Product) ->Unit){
        listener = l
    }

    //configuramos lo necesario para desplegar el RecyclerView
    private fun setUpRecyclerView(){
        recyclerProducts.setHasFixedSize(true)
        recyclerProducts.layoutManager = LinearLayoutManager(activity)
        //seteando el Adapter
        mAdapter = RecyclerAdapter( requireActivity(), getProducts(), listener)
        //asignando el Adapter al RecyclerView
        recyclerProducts.adapter = mAdapter
    }

    //generamos datos dummy con este método
    private fun getProducts(): MutableList<Product>{
        var products:MutableList<Product> = ArrayList()

        products.add(Product("Blusa verde", "Disponible el 20 de noviembre", "$200",4.6f,R.drawable.image_m1))
        products.add(Product("Blusa rosa", "Disponible el 20 de noviembre", "$250",4.4f,R.drawable.image_m2))
        products.add(Product("Lentes", "Disponible Prime", "$300",3.8f,R.drawable.image_m3))
        products.add(Product("Gorro guinda", "Disponible Prime", "$150",4.8f,R.drawable.image_m4))

        products.add(Product("Blusa verde", "Disponible el 20 de noviembre", "$200",4.6f,R.drawable.image_h1))
        products.add(Product("Blusa rosa", "Disponible el 20 de noviembre", "$250",4.4f,R.drawable.image_h2))
        products.add(Product("Lentes", "Disponible Prime", "$300",3.8f,R.drawable.image_h3))
        products.add(Product("Gorro guinda", "Disponible Prime", "$150",4.8f,R.drawable.image_h4))

        products.add(Product("Blusa verde", "Disponible el 20 de noviembre", "$200",4.6f,R.drawable.image_n1))
        products.add(Product("Blusa rosa", "Disponible el 20 de noviembre", "$250",4.4f,R.drawable.image_n2))
        products.add(Product("Lentes", "Disponible Prime", "$300",3.8f,R.drawable.image_n3))
        products.add(Product("Gorro guinda", "Disponible Prime", "$150",4.8f,R.drawable.image_n4))
        return products
    }
}