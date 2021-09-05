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

class ConfigPaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_payment)

        val return_icon = findViewById<Button>(R.id.config_pay_btn_return)
        val edit_credit = findViewById<Button>(R.id.config_pay_btn_edit_credit)
        val edit_debit = findViewById<Button>(R.id.config_pay_btn_edit_debit)
        val inp_credit = findViewById<EditText>(R.id.config_pay_input_credit)
        val inp_debit = findViewById<EditText>(R.id.config_pay_input_debit)
        val btn_update = findViewById<Button>(R.id.config_pay_btn_update)

        return_icon.setOnClickListener {
            finish()
        }

        edit_credit.setOnClickListener{
            inp_credit.isEnabled = true
        }

        edit_debit.setOnClickListener{
            inp_debit.isEnabled = true
        }

        inp_credit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_update.isVisible = true
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        inp_debit.addTextChangedListener(object : TextWatcher {
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

        /*returnIcon = findViewById(R.id.config_data_btn_return)
        editDebitCard = findViewById(R.id.btn_EditDebitCard)
        editCreditCard = findViewById(R.id.btn_EditCreditCard)
        inputDebitCard = findViewById(R.id.input_DebitCard)
        inputCreditCard = findViewById(R.id.input_CreditCard)
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
            inputCreditCard.hint = regUser.getCreditCard()
            inputDebitCard.hint = regUser.getDebitCard()

            editDebitCard.setOnClickListener{
                inputDebitCard.isEnabled = true
            }

            editCreditCard.setOnClickListener{
                inputCreditCard.isEnabled = true
            }

            inputCreditCard.addTextChangedListener(object : TextWatcher {
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
                buttonShowDialog_onClick(regUser)
            }
        }else {
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
            if (inputCreditCard.text.toString()!="") regUser.setCreditCard(inputCreditCard.text.toString())
            if (inputDebitCard.text.toString()!="") regUser.setDebitCard(inputDebitCard.text.toString())
            Toast.makeText(applicationContext, "Tus datos han sido actualizados", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }*/
}