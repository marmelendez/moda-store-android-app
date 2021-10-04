package org.bedu.modastoreapp

import android.animation.AnimatorInflater
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import org.bedu.modastoreapp.modelos.RegisteredUser

class ConfigPaymentActivity : AppCompatActivity() {
    private lateinit var returnIcon: Button
    private lateinit var editDebitCard: Button
    private lateinit var editCreditCard: Button
    private lateinit var inputDebitCard: EditText
    private lateinit var inputCreditCard: EditText
    private lateinit var updateButton: Button

    companion object {
        const val CHANNEL_CLIENT = "CHANNEL_COURSES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_payment)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }

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
            "No"
        ) { dialog, which -> }

        builder.setPositiveButton(
            "Si"
        ) { dialogInterface, i ->
            if (inputCreditCard.text.toString()!="") regUser.setCreditCard(inputCreditCard.text.toString())
            if (inputDebitCard.text.toString()!="") regUser.setDebitCard(inputDebitCard.text.toString())
            Toast.makeText(applicationContext, "Tus datos han sido actualizados", Toast.LENGTH_SHORT).show()
            simpleNotification()
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotificationChannel() {
        val name = getString(R.string.channel_client)
        val descriptionText = getString(R.string.transaction_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(CartActivity.CHANNEL_CLIENT, name, importance).apply {
            description = descriptionText
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

    private fun simpleNotification(){
        val notification = NotificationCompat.Builder(this, CHANNEL_CLIENT)
            .setSmallIcon(R.drawable.ic_logo)
            .setColor(ContextCompat.getColor(this,R.color.blue))
            .setContentTitle(getString(R.string.action_title_cards_config))
            .setContentText(getString(R.string.action_body_cards_config))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).run{
            notify(23,notification)
        }
    }

}