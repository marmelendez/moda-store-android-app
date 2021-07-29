package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible

class ConfigAddressActivity : AppCompatActivity() {
    private lateinit var returnIcon: Button
    private lateinit var editAddress: Button
    private lateinit var inputAddress: EditText
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_address)

        returnIcon = findViewById(R.id.btn_Return)
        editAddress = findViewById(R.id.btn_EditAddress)
        inputAddress = findViewById(R.id.input_Address)
        updateButton = findViewById(R.id.btn_Submit)

        returnIcon.setOnClickListener {
            val intent = Intent(this, ConfigurationActivity::class.java)
            startActivity(intent)
        }

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
            Toast.makeText(applicationContext ,"Tus datos han sido actualizados", Toast.LENGTH_LONG).show()
        }
    }
}