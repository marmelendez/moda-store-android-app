package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ConfigurationActivity : AppCompatActivity() {
    private lateinit var btn_return: Button
    private lateinit var btn_Account: Button
    private lateinit var btn_Payment: Button
    private lateinit var btn_Address: Button
    //private lateinit var btn4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        btn_return = findViewById(R.id.btn_Return)
        btn_Account = findViewById(R.id.btn_Account)
        btn_Payment = findViewById(R.id.btn_PaymentMethod)
        btn_Address = findViewById(R.id.btn_Addresses)
        //btn4 = findViewById(R.id.btn_LogOut)


        btn_return.setOnClickListener {
            val intent = Intent(this, ConfigurationActivity::class.java)
            startActivity(intent)
        }


        btn_Account.setOnClickListener{
            val intent = Intent(this, ConfigAccountDataActivity::class.java)
            startActivity(intent)
        }

        btn_Payment.setOnClickListener{
            val intent = Intent(this, ConfigPaymentActivity::class.java)
            startActivity(intent)
        }

        btn_Address.setOnClickListener{
            val intent = Intent(this, ConfigAddressActivity::class.java)
            startActivity(intent)
        }
    }
}