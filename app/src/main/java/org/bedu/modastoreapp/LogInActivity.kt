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

class LogInActivity : AppCompatActivity() {
    private lateinit var buttonLogIn: Button
    private lateinit var buttonSignIn: Button
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

        buttonLogIn = findViewById(R.id.buttonLogin)
        buttonSignIn = findViewById(R.id.buttonSign)
        warningUser = findViewById(R.id.warningUser)
        warningPassword = findViewById(R.id.warningPassword)
        inputName = findViewById(R.id.textName)
        inputPassword = findViewById(R.id.textPassword)

        warningUser.isVisible = false
        warningPassword.isVisible = false
        inputPassword.isVisible = false


        buttonLogIn.isEnabled = false

        inputName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (myStore.getUser(inputName.text.toString()) == null) {
                    warningUser.isVisible = true
                    warningUser.setText("No se encontro un usuario registrado con ese nombre")
                } else {
                    warningUser.isVisible = false
                    inputPassword.isVisible = true
                    Toast.makeText(applicationContext ,"Hola de nuevo ${inputName.text}", Toast.LENGTH_LONG).show()
                    regUser = myStore.getUser(inputName.text.toString())!!
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        inputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (regUser.getPassword() != inputPassword.text.toString()) {
                    warningPassword.isVisible = true
                    warningPassword.setText("Contraseña incorrecta")
                } else {
                    warningPassword.isVisible = false
                    buttonLogIn.isEnabled = true
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })


        buttonLogIn.setOnClickListener {
            //validar datos nuevamente

            val intent = Intent(this, ConfigurationActivity::class.java)
            startActivity(intent)

            /*val boxName = Bundle()
            boxName.putString(USER_NAME, input.text.toString())

            val intent = Intent(this, ConfigurationActivity::class.java).apply {
                putExtras(boxName)
            }
            startActivity(intent)*/
        }

        buttonSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}