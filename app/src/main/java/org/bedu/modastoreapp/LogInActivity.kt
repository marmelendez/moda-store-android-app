package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import org.bedu.modastoreapp.modelos.BaseDatos
import org.bedu.modastoreapp.modelos.RegisteredUser

val MYSTORE = BaseDatos.start()
const val USERNAME = "org.bedu.activity.USERNAME"
const val PRODUCTID = "org.bedu.activity.PRODUCTID"

class LogInActivity : AppCompatActivity() {
    private lateinit var buttonLogIn: Button
    private lateinit var buttonSignIn: Button
    private lateinit var warningUser: TextView
    private lateinit var warningPassword: TextView
    private lateinit var inputName: EditText
    private lateinit var inputPassword: EditText
    private lateinit var regUser: RegisteredUser
    private var inpUser = ""
    private var inpPassword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        /*Quito AppBar
        supportActionBar?.hide()

        //Conexion a Sign In
        var newAccount: TextView = findViewById(R.id.textCreate)

        newAccount.setOnClickListener {
          val intent = Intent(this, SignInActivity::class.java)

            startActivity(intent)
        }*/
        
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
                if (MYSTORE.getUserName(inputName.text.toString()) == null) {
                    warningUser.isVisible = true
                    warningUser.setText("Usuario no registrado")
                    buttonLogIn.isEnabled = false
                } else {
                    inpUser = inputName.text.toString()
                    warningUser.isVisible = false
                    inputPassword.isVisible = true
                    regUser = MYSTORE.getUserName(inputName.text.toString())!!
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
                    buttonLogIn.isEnabled = false
                } else {
                    inpPassword = inputPassword.text.toString()
                    warningPassword.isVisible = false
                    buttonLogIn.isEnabled = true
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })


        buttonLogIn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, inputName.text.toString())

            val intent = Intent(this, ShopActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }

        buttonSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}