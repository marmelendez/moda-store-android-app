package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import org.bedu.modastoreapp.modelos.BaseDatos
import org.bedu.modastoreapp.modelos.RegisteredUser
import org.bedu.modastoreapp.modelos.Store
import org.bedu.modastoreapp.modelos.User

class LogInActivity : AppCompatActivity() {
    private lateinit var boton: Button
    private lateinit var warningUser: TextView
    private lateinit var warningPassword: TextView
    private lateinit var inputName: EditText
    private lateinit var inputPassword: EditText
    private lateinit var regUser: RegisteredUser
    private lateinit var inpUser: String
    private lateinit var inpPassword: String


    val myStore: Store = BaseDatos.start()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        var inpUser = ""
        val user = "maribel"
        val password = "123"

        boton = findViewById(R.id.buttonLogin)
        warningUser = findViewById(R.id.warningUser)
        warningPassword = findViewById(R.id.warningPassword)
        inputName = findViewById(R.id.textName)
        inputPassword = findViewById(R.id.textPassword)

        warningUser.isVisible = false
        warningPassword.isVisible = false
        inputPassword.isVisible = false


        boton.isEnabled = false

        inputName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (inputName.text.toString() != user) {
                    warningUser.isVisible = true
                    warningUser.setText("No se encontro un usuario registrado con ese nombre")
                } else {
                    warningUser.isVisible = false
                    Toast.makeText(applicationContext ,"Hola de nuevo ${inputName.text}", Toast.LENGTH_LONG).show()
                    inputPassword.isVisible = true
                }

                /*
                if (myStore.getUser(inputName.text.toString()) == null) {
                    Toast.makeText(applicationContext ,"No se encontro un usuario registrado con ese nombre", Toast.LENGTH_LONG).show()
                }
                regUser = myStore.getUser(inputName.text.toString())!!
                inputPassword.isVisible = true*/
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        inputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (inputPassword.text.toString() != password) {
                    warningPassword.isVisible = true
                    warningPassword.setText("Contraseña incorrecta")
                } else {
                    warningPassword.isVisible = false
                    Toast.makeText(applicationContext, "Contraseña correcta :)", Toast.LENGTH_LONG).show()
                    boton.isEnabled = true
                }
                    /*if (regUser.getPassword() != inputPassword.text.toString()) {
                        Toast.makeText(applicationContext ,"Contraseña incorrecta", Toast.LENGTH_LONG).show()
                    }*/
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