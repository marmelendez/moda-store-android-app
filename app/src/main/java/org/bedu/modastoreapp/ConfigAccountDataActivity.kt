package org.bedu.modastoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ConfigAccountDataActivity : AppCompatActivity() {
    private lateinit var returnIcon: Button
    private lateinit var editUsernameIcon: Button
    private lateinit var editEmailIcon: Button
    private lateinit var editTelephoneIcon: Button
    private lateinit var editPasswordIcon: Button
    private lateinit var inputUsername: EditText
    private lateinit var inputEmail: EditText
    private lateinit var inputTelephone: EditText
    private lateinit var inputPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_account_data)

        returnIcon = findViewById(R.id.btn_Return)
        editUsernameIcon = findViewById(R.id.btn_EditUsername)
        editEmailIcon = findViewById(R.id.btn_EditEmail)
        editTelephoneIcon = findViewById(R.id.btn_EditTelephone)
        editPasswordIcon = findViewById(R.id.btn_EditPassword)
        inputUsername = findViewById(R.id.input_Username)
        inputEmail = findViewById(R.id.input_Email)
        inputTelephone = findViewById(R.id.input_Telephone)
        inputPassword = findViewById(R.id.input_Password)

        returnIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        editUsernameIcon.setOnClickListener{
            inputUsername.isEnabled = true
        }

        editEmailIcon.setOnClickListener{
            inputEmail.isEnabled = true
        }

        editTelephoneIcon.setOnClickListener{
            inputTelephone.isEnabled = true
        }

        editPasswordIcon.setOnClickListener{
            inputPassword.isEnabled = true
        }
    }
}