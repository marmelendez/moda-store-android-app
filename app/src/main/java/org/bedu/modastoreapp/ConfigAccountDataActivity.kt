package org.bedu.modastoreapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import org.bedu.modastoreapp.modelos.RegisteredUser

class ConfigAccountDataActivity : AppCompatActivity() {
    private lateinit var returnIcon: Button
    private lateinit var editUsernameIcon: Button
    private lateinit var editEmailIcon: Button
    private lateinit var editPasswordIcon: Button
    private lateinit var inputUsername: EditText
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_account_data)

        returnIcon = findViewById(R.id.btn_Return)
        editUsernameIcon = findViewById(R.id.btnEditUserName)
        editEmailIcon = findViewById(R.id.btnEditEmail)
        editPasswordIcon = findViewById(R.id.btnEditPassword)
        inputUsername = findViewById(R.id.inpUsername)
        inputEmail = findViewById(R.id.inpEmail)
        inputPassword = findViewById(R.id.inpPassword)
        updateButton = findViewById(R.id.btn_Submit)

        val bundle = intent.extras
        val username = bundle?.getString(USERNAME)
        val regUser = STORE.getUserName(username.toString())

        returnIcon.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, regUser?.getName().toString())

            val intent = Intent(this, ConfigurationActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }

        if (regUser != null) {
            inputUsername.hint = regUser.getName()
            inputEmail.hint = regUser.getEmail()

            editUsernameIcon.setOnClickListener{
                inputUsername.isEnabled = true
            }

            editEmailIcon.setOnClickListener{
                inputEmail.isEnabled = true
            }

            editPasswordIcon.setOnClickListener{
                inputPassword.isEnabled = true
            }

            inputUsername.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    updateButton.isVisible = true
                }
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })

            inputEmail.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    updateButton.isVisible = true
                }
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })

            inputPassword.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    updateButton.isVisible = true
                }
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })

            updateButton.setOnClickListener {
                buttonShowDialog_onClick(regUser)
            }

        } else {
            Toast.makeText(applicationContext ,"Inicia sesión o crea una cuenta :)" , Toast.LENGTH_LONG).show()
        }
    }

    private fun buttonShowDialog_onClick(regUser: RegisteredUser) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Actualización de datos")
        builder.setMessage("¿Deseas cambiar tus datos?")

        builder.setNegativeButton(
            "Cancelar"
        ) { dialog, which -> }

        builder.setPositiveButton(
            R.string.ok
        ) { dialogInterface, i ->
            if (inputUsername.text.toString()!="") regUser.setName(inputUsername.text.toString())
            if (inputEmail.text.toString()!="") regUser.setEmail(inputEmail.text.toString())
            if (inputPassword.text.toString()!="") regUser.setPassword(inputPassword.text.toString())
            Toast.makeText(applicationContext, "Tus datos han sido actualizados", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}