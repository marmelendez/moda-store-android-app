package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        var buttonLogin: Button = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            val intent2 = Intent(this, ConfigurationActivity::class.java)
            startActivity(intent2)
        }

        //Quito AppBar
        supportActionBar?.hide()

        //Conexion a Sign In
        var newAccount: TextView = findViewById(R.id.textCreate)

        newAccount.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}