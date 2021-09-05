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
import androidx.recyclerview.widget.LinearLayoutManager

import android.graphics.ColorSpace.Model

class FragmentSearchList : Fragment() {

    var recyclerView: RecyclerView? = null
    val itemList = STORE.catalogProduct

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_search_list, container, false)
        recyclerView = view.findViewById(R.id.home_product_list)
        recyclerView.setLayoutManager(LinearLayoutManager(context))

        //initData();
        recyclerView.setAdapter(ItemAdapter(initData(), context))
        return view
    }


    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_search_list, container, false)

        val products = STORE.catalogProduct
        val dataList = view.findViewById<RecyclerView>(R.id.home_product_list)

        val adapter = RecyclerAdapter(this, products, "")
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        dataList.setLayoutManager(gridLayoutManager)
        dataList.setAdapter(adapter)

        return inflater.inflate(R.layout.fragment_search_list, container, false)
    }*/
}