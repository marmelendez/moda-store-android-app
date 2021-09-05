package org.bedu.modastoreapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible

class ConfigAccountDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_account_data)

        val return_icon = findViewById<Button>(R.id.config_data_btn_return)
        val edit_username = findViewById<Button>(R.id.config_data_btn_edit_username)
        val edit_email = findViewById<Button>(R.id.config_data_btn_edit_email)
        val edit_password = findViewById<Button>(R.id.config_data_btn_edit_password)
        val inp_username = findViewById<EditText>(R.id.config_data_input_username)
        val inp_email = findViewById<EditText>(R.id.config_data_input_email)
        val inpt_password = findViewById<EditText>(R.id.config_data_input_password)
        val btn_update = findViewById<Button>(R.id.config_data_btn_update)

        return_icon.setOnClickListener {
            finish()
        }

        edit_username.setOnClickListener{
            inp_username.isEnabled = true
        }

        edit_email.setOnClickListener{
            inp_email.isEnabled = true
        }

        edit_password.setOnClickListener{
            inpt_password.isEnabled = true
        }

        inp_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_update.isVisible = true
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        inp_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_update.isVisible = true
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        inpt_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_update.isVisible = true
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        btn_update.setOnClickListener {
            confirmDialog()
        }

        /*if (regUser != null) {
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
        }*/
    }

    private fun confirmDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Actualización de datos")
        builder.setMessage("¿Deseas actualizar tus datos?")

        builder.setNegativeButton(
            "No"
        ) { dialog, which -> }

        builder.setPositiveButton(
            "Si"
        ) { dialogInterface, i ->
            //actualizar
            Toast.makeText(applicationContext, "Tus datos han sido actualizados", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}