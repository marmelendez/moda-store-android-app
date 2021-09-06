package org.bedu.modastoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.bedu.modastoreapp.retrofit.interfaz.JsonPlaceHolderAPI
import org.bedu.modastoreapp.retrofit.modelos.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommunityActivity : AppCompatActivity() {

    private lateinit var jsonText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        jsonText = findViewById(R.id.comunnity_json_text)
        getPosts()

    }

    private fun getPosts() {
        val retrofit:Retrofit = Retrofit.Builder()
                            .baseUrl("https://jsonplaceholder.typicode.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
        val jsonPlaceholder = retrofit.create(JsonPlaceHolderAPI::class.java)
        val call = jsonPlaceholder.getPosts()


        call?.enqueue(object : Callback<List<Post?>?> {

            override fun onFailure(call: Call<List<Post?>?>, t: Throwable) {
                jsonText.text = "Codigo:${t.message}"
            }

            override fun onResponse(call: Call<List<Post?>?>, response: Response<List<Post?>?>) {
                if (!response.isSuccessful()) {
                    jsonText.text = "Codigo:${response.code()}"
                    return
                }

                val postList = response.body()

                if (postList != null) {
                    for (post in postList) {
                        val content = "\n--------------------------------------" +
                                "\n\nPost: ${post?.id} \n" +
                                " ${post?.body} \n"
                        jsonText.append(content)
                    }
                }
            }

        })


    }
}