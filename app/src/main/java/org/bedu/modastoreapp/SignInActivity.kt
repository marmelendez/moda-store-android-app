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
import org.bedu.modastoreapp.modelos.RegisteredUser
import org.bedu.modastoreapp.modelos.Form.*

class SignInActivity : AppCompatActivity() {
    private lateinit var returnIcon: Button
    private lateinit var signInButton: Button
    private lateinit var inputUsername: EditText
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var warnUsername: TextView
    private lateinit var warnEmail: TextView
    private lateinit var warnPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        returnIcon = findViewById(R.id.config_data_btn_return)
        signInButton = findViewById(R.id.btn_SignIn)
        inputUsername = findViewById(R.id.inp_AskUsername)
        inputEmail = findViewById(R.id.inp_AskEmail)
        inputPassword = findViewById(R.id.inp_AskPassword)
        warnUsername = findViewById(R.id.username_error)
        warnEmail = findViewById(R.id.email_error)
        warnPassword = findViewById(R.id.password_error)

        inputEmail.isVisible = false
        inputPassword.isVisible = false
        warnUsername.isVisible = false
        warnEmail.isVisible = false
        warnPassword.isVisible = false

        returnIcon.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        inputUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (!validateUsername(inputUsername.text.toString())) {
                    warnUsername.isVisible = true
                    warnUsername.setText("Ingresa un nombre de usuario valido: empieza con letra, minimo 6 caracteres, letras y numeros")
                } else if (!STORE.isInListOfUsersUsername(inputUsername.text.toString())) {
                    warnUsername.isVisible = true
                    warnUsername.setText("Este nombre de usuario ya esta registrado, escoge otro disponible")
                } else {
                    warnUsername.isVisible = false
                    inputEmail.isVisible = true
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        inputEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (!validateEmail(inputEmail.text.toString())) {
                    warnEmail.isVisible = true
                    warnEmail.setText("Ingresa un correo valido: termina con @domain.com")
                } else if (!STORE.isInListOfUsersEmail(inputEmail.text.toString())) {
                    warnEmail.isVisible = true
                    warnEmail.setText("Este correo ya esta registrado")
                } else {
                    warnEmail.isVisible = false
                    inputPassword.isVisible = true
                    warnPassword.isVisible = true
                    warnPassword.text = "Te recomendamos ${passwordGenerator()}"
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        inputPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (!validatePassword(inputPassword.text.toString())) {
                    warnPassword.text = "Ingresa una contraseña valida: minimo 8,contiene letras, numeros, . / _"
                } else {
                    warnPassword.isVisible = false
                    signInButton.isVisible = true
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        signInButton.setOnClickListener {
            STORE.addUser(RegisteredUser(STORE.catalogProduct.size,inputUsername.text.toString(), inputEmail.text.toString(),inputPassword.text.toString()))
            Toast.makeText(applicationContext ,"Bienvenido ${inputUsername.text}", Toast.LENGTH_LONG).show()

            val bundle = Bundle()
            bundle.putString(USERNAME, inputUsername.text.toString())

            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }
    }
}