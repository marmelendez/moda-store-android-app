package org.bedu.modastoreapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.itesm.clothingstore.ui.login.Utility
import mx.itesm.clothingstore.ui.login.Utility.hideKeyboard
import org.bedu.modastoreapp.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        FirebaseApp.initializeApp(this)

        auth = Firebase.auth

        handleClick()
    }

    private fun handleClick() {

        binding.btnRegister.setOnClickListener {
            it.hideKeyboard()

            binding.btnRegister.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            createAccount(email, password)
        }

        binding.email.doAfterTextChanged {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            binding.btnRegister.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }

        binding.password.doAfterTextChanged {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            binding.btnRegister.isEnabled = email.isNotEmpty() && password.isNotEmpty()
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user, null)
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    updateUI(null, task.exception)
                }
            }
    }

    private fun sendEmailVerification() {
    }

    private fun updateUI(user: FirebaseUser?, exception: Exception?) {
        if (exception != null) {
            binding.loading.visibility = View.GONE
            binding.btnRegister.visibility = View.VISIBLE
            Utility.displaySnackBar(binding.root, exception.message.toString(), this, R.color.red)
        } else {
            Utility.displaySnackBar(binding.root, "Registro exitoso", this, R.color.green)
            binding.loading.visibility = View.GONE
            binding.btnRegister.visibility = View.VISIBLE
        }
    }


    private fun reload() {

    }

    companion object {
        private const val TAG = "EmailPassword"
    }

}