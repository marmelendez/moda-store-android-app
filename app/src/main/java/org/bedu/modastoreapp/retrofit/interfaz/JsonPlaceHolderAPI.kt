package org.bedu.modastoreapp.retrofit.interfaz

import org.bedu.modastoreapp.retrofit.modelos.Post
import retrofit2.Call
import retrofit2.http.GET


interface JsonPlaceHolderAPI {
    companion object {
    }

    @GET("posts")
    fun getPosts(): Call<List<Post?>?>?
}