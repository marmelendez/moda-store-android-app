package org.bedu.modastoreapp

import android.animation.AnimatorInflater
import android.app.ActivityOptions
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.transition.Transition
import android.transition.TransitionInflater
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.view.View
import org.bedu.modastoreapp.modelos.BaseDatos


val MYSTORE = BaseDatos.start()
const val USERNAME = "org.bedu.activity.USERNAME" //ubicacion donde el bundle guardara variable
const val PRODUCTID = "org.bedu.activity.PRODUCTID" //ubicacion donde el bundle guardara variable

class StartActivity : AppCompatActivity() {

    private lateinit var inp_username: EditText
    private lateinit var inp_password: EditText
    private lateinit var btn_login: Button
    private lateinit var btn_signin: Button
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        inp_username = findViewById(R.id.start_input_username)
        inp_password = findViewById(R.id.start_input_password)
        btn_login = findViewById(R.id.start_btn_login)
        btn_signin = findViewById(R.id.start_btn_signin)


        val transitionXml = TransitionInflater
            .from(this).inflateTransition(R.transition.transition_activity).apply {
                excludeTarget(window.decorView.findViewById<View>(R.id.action_bar_container), true)
                excludeTarget(android.R.id.statusBarBackground, true)
                excludeTarget(android.R.id.navigationBarBackground, true)
            }


        window.exitTransition = transitionXml


        btn_login.setOnClickListener {
            shrink(btn_login)
            if (inp_username.text != null && inp_username.text != null) {
                iniciarSesion(inp_username, inp_password)
            } else {
                Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show()
            }
        }

        btn_signin.setOnClickListener {
            shrink(btn_signin)
            if (inp_username.text != null && inp_username.text != null) {
                registrarse(inp_username, inp_password)
            } else {
                Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show()
            }
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
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
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
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
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
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun shrink(button: Button) {
        AnimatorInflater.loadAnimator(this, R.animator.shrink).apply {
            setTarget(button)
            start()
        }
    }

    // Obtener preferencias
    private fun getPreferences() {
        prefs = getSharedPreferences(FILE, MODE_PRIVATE)
        //r = prefs.getInt("valorR", 255)
        //g = prefs.getInt("valorG", 255)
        //b = prefs.getInt("valorB", 255)
        Toast.makeText(this, "BACKGROUND COLOR CHANGED", Toast.LENGTH_SHORT).show()
    }

    // Guardar preferencias
    private fun savePreferences() {
        val editor = prefs.edit()
        //editor.putInt("valorR", r)
        //editor.putInt("valorG", g)
        //editor.putInt("valorB", b)
        editor.apply()
    }

    companion object {
        private const val FILE = "MisPrefs"
    }

}

    /*private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.start_fragment, fragment)
        fragmentTransaction.commit()
    }*/