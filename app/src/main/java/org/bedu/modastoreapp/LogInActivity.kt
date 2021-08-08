package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isVisible

class LogInActivity : AppCompatActivity() {
    private lateinit var boton: Button
    private lateinit var inputName: EditText
    private lateinit var inputPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        boton = findViewById(R.id.buttonLogin)
        inputName = findViewById(R.id.textName)
        inputPassword = findViewById(R.id.textPassword)
        inputPassword.isVisible = false

        boton.isEnabled = false

        inputName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                //check if is valid
                inputPassword.isVisible = true
                //si no es valido mostrar toast


            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        inputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                //check if is valid
                //si no es valido mostrar toast
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        //si input password e input name son validos activar boton de input

        boton.setOnClickListener {
            val intent = Intent(this, ConfigurationActivity::class.java)
            startActivity(intent)
            /*val boxName = Bundle()
            boxName.putString(USER_NAME, input.text.toString())

            val intent = Intent(this, ConfigurationActivity::class.java).apply {
                putExtras(boxName)
            }
            startActivity(intent)*/
        }
    }
}