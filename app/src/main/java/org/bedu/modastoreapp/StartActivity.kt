package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class StartActivity : AppCompatActivity() {

    private lateinit var inp_username: EditText
    private lateinit var inp_password: EditText
    private lateinit var btn_login: Button
    private lateinit var btn_signin: Button
    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        inp_username = findViewById(R.id.start_input_username)
        inp_password = findViewById(R.id.start_input_password)
        btn_login = findViewById(R.id.start_btn_login)
        btn_signin = findViewById(R.id.start_btn_signin)

        btn_login.setOnClickListener {
            iniciarSesion(inp_username, inp_password)
        }

        btn_signin.setOnClickListener {
            registrarse(inp_username, inp_password)
        }
    }

    fun iniciarSesion(username: EditText, password: EditText) {
        Firebase.auth.signInWithEmailAndPassword(
            username.text.toString(),
            password.text.toString()
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "INICIO DE SESIÓN EXITOSO", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "ERROR: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }

    fun registrarse(username: EditText, password: EditText) {
        Firebase.auth.createUserWithEmailAndPassword(
            username.text.toString(),
            password.text.toString()
        )
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "ERROR: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }

    override fun onStart() {
        super.onStart()
        verificarUsuarioGeneral() }

    fun verificarUsuarioGeneral() {
        if (Firebase.auth.currentUser == null) {
            Toast.makeText(this, "SIN USUARIO",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, Firebase.auth.currentUser?.email,Toast.LENGTH_SHORT).show()
        }
    }

}

    /*private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.start_fragment, fragment)
        fragmentTransaction.commit()
    }*/