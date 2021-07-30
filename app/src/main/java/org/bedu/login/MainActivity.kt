package org.bedu.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

const val USER_NAME = "org.bedu.MyActivity.USER_NAME"

class MainActivity : AppCompatActivity() {

    private lateinit var boton: Button
    private lateinit var input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boton = findViewById(R.id.buttonLogin)
        input = findViewById(R.id.textName)

        boton.setOnClickListener {
            val boxName = Bundle()
            boxName.putString(USER_NAME, input.text.toString())

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtras(boxName)
            }
            startActivity(intent)
        }
    }
}