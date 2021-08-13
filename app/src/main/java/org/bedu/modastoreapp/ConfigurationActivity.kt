package org.bedu.modastoreapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import org.bedu.modastoreapp.modelos.BaseDatos
import org.bedu.modastoreapp.modelos.RegisteredUser
import org.bedu.modastoreapp.modelos.Store

class ConfigurationActivity : AppCompatActivity() {
    private lateinit var btn_return: Button
    private lateinit var btn_Account: Button
    private lateinit var btn_Payment: Button
    private lateinit var btn_Address: Button
    private lateinit var btn_LogOut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        btn_return = findViewById(R.id.btn_Return)
        btn_Account = findViewById(R.id.btn_Account)
        btn_Payment = findViewById(R.id.btn_PaymentMethod)
        btn_Address = findViewById(R.id.btn_Addresses)
        btn_LogOut = findViewById(R.id.btn_LogOut)

        val bundle = intent.extras
        val userName = bundle?.getString(USERNAME)

        btn_return.setOnClickListener {
            finish()
        }

        btn_LogOut.setOnClickListener {
            buttonShowDialog_onClick()
        }

        btn_Account.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(USERNAME, userName)

            val intent = Intent(this, ConfigAccountDataActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }

        btn_Payment.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(USERNAME, userName)

            val intent = Intent(this, ConfigPaymentActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }

        btn_Address.setOnClickListener{
            val bundle = Bundle()
            bundle.putString(USERNAME, userName)

            val intent = Intent(this, ConfigAddressActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }
    }

    private fun buttonShowDialog_onClick() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Cerrar sesión")
        builder.setMessage("¿Deseas salir de tu cuenta?")

        builder.setNegativeButton(
            "Cancelar"
        ) { dialog, which -> }

        builder.setPositiveButton(
            R.string.ok
        ) { dialogInterface, i ->
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "Nos vemos!", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}