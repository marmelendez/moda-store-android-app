package org.bedu.modastoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible

class SignInActivity : AppCompatActivity() {
    private lateinit var btn_return: Button
    private lateinit var btn_signin: Button
    private lateinit var input_username: EditText
    private lateinit var input_email: EditText
    private lateinit var input_password: EditText
    private lateinit var warn_username: TextView
    private lateinit var warn_email: TextView
    private lateinit var warn_password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btn_return = findViewById(R.id.btn_Return)
        btn_signin = findViewById(R.id.btn_SignIn)
        input_username = findViewById(R.id.inp_AskUsername)
        input_email = findViewById(R.id.inp_AskEmail)
        input_password = findViewById(R.id.inp_AskPassword)
        warn_username = findViewById(R.id.username_error)
        warn_email = findViewById(R.id.email_error)
        warn_password = findViewById(R.id.password_error)

        warn_username.isVisible = false
        warn_email.isVisible = false
        warn_password.isVisible = false



    }
}