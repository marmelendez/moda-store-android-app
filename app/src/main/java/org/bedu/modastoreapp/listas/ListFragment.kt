package org.bedu.modastoreapp.listas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import org.bedu.modastoreapp.MYSTORE
import org.bedu.modastoreapp.modelos.ProductPar
import org.bedu.modastoreapp.R


class ListFragment : Fragment() {

    private lateinit var mAdapter : RecyclerAdapterShop
    private var listener : (ProductPar) ->Unit = {}
    private lateinit var username : String

    fun setUsername(username : String) {
        this.username = username
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerView()
    }

    fun setListener(l: (ProductPar) ->Unit){
        listener = l
    }

    private fun setUpRecyclerView(){
        recyclerProducts.setHasFixedSize(true)
        recyclerProducts.layoutManager = LinearLayoutManager(activity)
        mAdapter = RecyclerAdapterShop (requireActivity(),getProducts())
        recyclerProducts.adapter = mAdapter
    }

    private fun getProducts(): MutableList<org.bedu.modastoreapp.modelos.Product> {
        if (MYSTORE.getUserName(username)?.getShoppingCart() != null) {
            return MYSTORE.getUserName(username)!!.getShoppingCart()
        } else {
            return MYSTORE.catalogProduct
        }
    }
}