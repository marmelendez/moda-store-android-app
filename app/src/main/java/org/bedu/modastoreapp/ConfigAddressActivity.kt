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

class ConfigAddressActivity : AppCompatActivity() {
    private lateinit var returnIcon: Button
    private lateinit var editAddress: Button
    private lateinit var inputAddress: EditText
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_address)
    }

        /*returnIcon = findViewById(R.id.config_data_btn_return)
        editAddress = findViewById(R.id.btn_EditAddress)
        inputAddress = findViewById(R.id.input_Address)
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
            inputAddress.hint = regUser.getAddress()

            editAddress.setOnClickListener{
                inputAddress.isEnabled = true
            }

            inputAddress.addTextChangedListener(object : TextWatcher {
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
            "ok"
        ) { dialogInterface, i ->
            if (inputAddress.text.toString()!="") regUser.setAddress(inputAddress.text.toString())
            Toast.makeText(applicationContext, "Tus datos han sido actualizados", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }*/
}

