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

class ConfigAddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_address)

        val return_icon = findViewById<Button>(R.id.config_address_btn_return)
        val edit_address = findViewById<Button>(R.id.config_address_btn_edit_address)
        val inp_address = findViewById<EditText>(R.id.config_address_input_address)
        val btn_update = findViewById<Button>(R.id.config_adress_btn_update)

        return_icon.setOnClickListener {
            finish()
        }

        edit_address.setOnClickListener{
            inp_address.isEnabled = true
        }

        inp_address.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_update.isVisible = true
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        btn_update.setOnClickListener {
            confirmDialog()
        }
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

