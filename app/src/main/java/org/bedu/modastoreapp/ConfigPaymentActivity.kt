package org.bedu.modastoreapp

import android.animation.AnimatorInflater
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
    private lateinit var returnIcon: Button
    private lateinit var editDebitCard: Button
    private lateinit var editCreditCard: Button
    private lateinit var inputDebitCard: EditText
    private lateinit var inputCreditCard: EditText
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_payment)

        returnIcon = findViewById(R.id.config_pay_btn_return)
        editDebitCard = findViewById(R.id.config_pay_btn_edit_debit)
        editCreditCard = findViewById(R.id.config_pay_btn_edit_credit)
        inputDebitCard = findViewById(R.id.config_pay_input_debit)
        inputCreditCard = findViewById(R.id.config_pay_input_credit)
        updateButton = findViewById(R.id.config_pay_btn_update)

        val bundle = intent.extras
        val username = bundle?.getString(USERNAME)
        val regUser = MYSTORE.getUserName(username.toString())

        returnIcon.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, regUser?.getName().toString())

            val intent = Intent(this, ProfileActivity::class.java).apply {
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
                shrink(updateButton)
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
            R.string.ok
        ) { dialogInterface, i ->
            if (inputCreditCard.text.toString()!="") regUser.setCreditCard(inputCreditCard.text.toString())
            if (inputDebitCard.text.toString()!="") regUser.setDebitCard(inputDebitCard.text.toString())
            Toast.makeText(applicationContext, "Tus datos han sido actualizados", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun shrink(button: Button) {
        AnimatorInflater.loadAnimator(this, R.animator.shrink).apply {
            setTarget(button)
            start()
        }
    }
}