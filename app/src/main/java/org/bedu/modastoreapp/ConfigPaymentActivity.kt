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

class ConfigPaymentActivity : AppCompatActivity() {
    private lateinit var returnIcon: Button
    private lateinit var editDebitCard: Button
    private lateinit var editCreditCard: Button
    private lateinit var inputDebitCard: EditText
    private lateinit var inputCreditCard: EditText
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_payment)

        returnIcon = findViewById(R.id.btn_Return)
        editDebitCard = findViewById(R.id.btn_EditDebitCard)
        editCreditCard = findViewById(R.id.btn_EditCreditCard)
        inputDebitCard = findViewById(R.id.input_DebitCard)
        inputCreditCard = findViewById(R.id.input_CreditCard)
        inputCreditCard = findViewById(R.id.input_CreditCard)
        updateButton = findViewById(R.id.btn_Submit)

        returnIcon.setOnClickListener {
            val intent = Intent(this, ConfigurationActivity::class.java)
            startActivity(intent)
        }

        editDebitCard.setOnClickListener{
            inputDebitCard.isEnabled = true
        }

        editCreditCard.setOnClickListener{
            inputCreditCard.isEnabled = true
        }

        inputDebitCard.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                updateButton.isVisible = true
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        inputDebitCard.addTextChangedListener(object : TextWatcher {
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