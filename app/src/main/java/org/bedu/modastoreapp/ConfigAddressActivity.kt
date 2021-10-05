package org.bedu.modastoreapp

import android.Manifest
import android.animation.AnimatorInflater
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import org.bedu.modastoreapp.modelos.RegisteredUser
import java.util.*

class ConfigAddressActivity : AppCompatActivity() {
    private lateinit var returnIcon: Button
    private lateinit var editAddress: Button
    private lateinit var obtenerDireccion: Button
    private lateinit var inputAddress: EditText
    private lateinit var updateButton: Button
    private lateinit var mFusedLocationClient : FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_address)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }

        returnIcon = findViewById(R.id.config_address_btn_return)
        editAddress = findViewById(R.id.config_address_btn_edit_address)
        inputAddress = findViewById(R.id.config_address_input_address)
        obtenerDireccion = findViewById(R.id.config_adress_btn_obtener)
        updateButton = findViewById(R.id.config_adress_btn_update)

        val bundle = intent.extras
        val username = bundle?.getString(USERNAME)
        val regUser = MYSTORE.getUserName(username.toString())
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        obtenerDireccion.setOnClickListener {
            getLocation()
            inputAddress.isEnabled = true
            updateButton.isVisible = true
        }
        //getLocation()

        returnIcon.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, regUser?.getName().toString())

            val intent = Intent(this, ProfileActivity::class.java).apply {
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
                shrink(updateButton)
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
            "No"
        ) { dialog, which -> }

        builder.setPositiveButton(
            "Si"
        ) { dialogInterface, i ->
            if (inputAddress.text.toString()!="") regUser.setAddress(inputAddress.text.toString())
            Toast.makeText(applicationContext, "Tus datos han sido actualizados", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
            simpleNotification()
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

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnCompleteListener(OnCompleteListener {
                    val location = it.result
                    if (location != null) {
                        try {
                            val geoCoder = Geocoder(this, Locale.getDefault())
                            val addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
                            inputAddress.setText(addresses[0].getAddressLine(0).toString())
                            // addresses[0].getLocality().toString()
                            // addresses[0].getCountryName().toString()
                        } catch (e : Exception) {
                            Toast.makeText(this, "No pudimos encontrar tu direccion", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        Toast.makeText(this, "No pudimos encontrar tu direccion", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        } else {
            requestPermission()

        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID)
    }

    private fun isLocationEnabled() : Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    companion object{
        const val PERMISSION_ID = 33
        const val CHANNEL_CLIENT = "CHANNEL_COURSES"
    }

    private fun checkGranted(permission : String) : Boolean {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermissions(): Boolean {
        return ( checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION) &&
                checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION) )
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
            .setContentTitle(getString(R.string.action_title_address_config))
            .setContentText(getString(R.string.action_body_address_config))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).run{
            notify(22,notification)
        }
    }
}


