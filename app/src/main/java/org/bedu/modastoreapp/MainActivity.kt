package org.bedu.modastoreapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        btn1 = findViewById(R.id.btn_Account)
        /*btn2 = findViewById(R.id.btn_Addresses)
        btn3 = findViewById(R.id.btn_PaymentMethod)
        btn4 = findViewById(R.id.btn_LogOut)*/


        btn1.setOnClickListener{
            val intent = Intent(this, ConfigAccountDataActivity::class.java)
            startActivity(intent)
        }

    }
}