package org.bedu.modastoreapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.bedu.modastoreapp.listas.RecyclerAdapter

class FragmentSearchList : Fragment(R.layout.fragment_search_list) {
    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_search_list, container, false)

        val products = STORE.catalogProduct
        val dataList = view.findViewById<RecyclerView>(R.id.home_product_list)

        val adapter = RecyclerAdapter(cont, products, "")
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        dataList.setLayoutManager(gridLayoutManager)
        dataList.setAdapter(adapter)

        return inflater.inflate(R.layout.fragment_search_list, container, false)
    }*/
}