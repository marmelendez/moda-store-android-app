package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LogInActivity : AppCompatActivity() {
    private lateinit var boton_log: Button
    private lateinit var boton_sign: Button
    private lateinit var input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        boton_log = findViewById(R.id.buttonLogin)
        boton_sign = findViewById(R.id.buttonSign)
        input = findViewById(R.id.textName)

        boton_log.setOnClickListener {
            val intent = Intent(this, ConfigurationActivity::class.java)
            startActivity(intent)
            /*val boxName = Bundle()
            boxName.putString(USER_NAME, input.text.toString())

            val intent = Intent(this, ConfigurationActivity::class.java).apply {
                putExtras(boxName)
            }
            startActivity(intent)*/
        }

        boton_sign.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


    }
}