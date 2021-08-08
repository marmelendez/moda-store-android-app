package org.bedu.modastoreapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.bedu.modastoreapp.modelos.RegisteredUser
import org.bedu.modastoreapp.modelos.BaseDatos
import org.bedu.modastoreapp.modelos.Store
import org.bedu.modastoreapp.modelos.User


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }
}