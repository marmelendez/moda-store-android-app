package org.bedu.modastoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_fragment)

        //Quito AppBar
        supportActionBar?.hide()
    }
}