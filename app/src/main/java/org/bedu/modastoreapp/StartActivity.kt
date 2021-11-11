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
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseUser
import mx.itesm.clothingstore.ui.login.Utility
import mx.itesm.clothingstore.ui.login.Utility.hideKeyboard
import org.bedu.modastoreapp.databinding.ActivityStartBinding
import org.bedu.modastoreapp.modelos.BaseDatos


val MYSTORE = BaseDatos.start()
const val USERNAME = "org.bedu.activity.USERNAME" //ubicacion donde el bundle guardara variable
const val PRODUCTID = "org.bedu.activity.PRODUCTID" //ubicacion donde el bundle guardara variable


class StartActivity : AppCompatActivity() {

    private lateinit var binding:ActivityStartBinding
    private lateinit var auth: FirebaseAuth

    private lateinit var inp_username: EditText
    private lateinit var inp_password: EditText
    private lateinit var btn_login: Button
    private lateinit var btn_signin: Button
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        FirebaseApp.initializeApp(this)

        auth = Firebase.auth

        handleClick()
/*
        inp_username = findViewById(R.id.start_input_username)
        inp_password = findViewById(R.id.start_input_password)
        btn_login = findViewById(R.id.btnLogin)
        btn_signin = findViewById(R.id.btnRegister)


        val transitionXml = TransitionInflater
            .from(this).inflateTransition(R.transition.transition_activity).apply {
                excludeTarget(window.decorView.findViewById<View>(R.id.action_bar_container), true)
                excludeTarget(android.R.id.statusBarBackground, true)
                excludeTarget(android.R.id.navigationBarBackground, true)
            }


        window.exitTransition = transitionXml

 */



    }

    private fun handleClick() {

        binding.btnLogin.setOnClickListener {
            it.hideKeyboard()

            binding.btnLogin.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE

            val email = binding.startInputUsername.text.toString()
            val password = binding.startInputPassword.text.toString()

            signIn(email, password)
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.startInputUsername.doAfterTextChanged {
            val email = binding.startInputUsername.text.toString()
            val password = binding.startInputPassword.text.toString()

            binding.btnLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
            binding.btnRegister.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }

        binding.startInputPassword.doAfterTextChanged {
            val email = binding.startInputUsername.text.toString()
            val password = binding.startInputPassword.text.toString()

            binding.btnLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty()
            binding.btnRegister.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }
    }


    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "INICIO DE SESIÓN EXITOSO")
                    val user = auth.currentUser
                    updateUI(user, null)
                } else {
                    Log.w(TAG, "ERROR", task.exception)
                    updateUI(null, task.exception)
                }
            }
    }


    private fun updateUI(user: FirebaseUser?, exception: Exception?) {
        if (exception != null) {
            binding.loading.visibility = View.GONE
            binding.btnLogin.visibility = View.VISIBLE
            Utility.displaySnackBar(binding.root, exception.message.toString(), this, R.color.red)
        } else {
            Utility.displaySnackBar(binding.root, "Inicio de sesión exitoso", this, R.color.green)
            binding.loading.visibility = View.GONE
            binding.btnLogin.visibility = View.VISIBLE
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    /*
    override fun onStart() {
        super.onStart()
        verificarUsuarioGeneral() }

     */

    /*
    fun verificarUsuarioGeneral() {
        if (Firebase.auth.currentUser == null) {
            Toast.makeText(this, "SIN USUARIO",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, Firebase.auth.currentUser?.email,Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

     */
/*
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

 */

    companion object {
        private const val FILE = "MisPrefs"
        private const val TAG = "EmailPassword"
    }

}
