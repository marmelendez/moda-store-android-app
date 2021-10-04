package org.bedu.modastoreapp

import android.animation.AnimatorInflater
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.bedu.modastoreapp.databinding.ActivityMainBinding
import org.bedu.modastoreapp.fragmentos.DetailActivity
import org.bedu.modastoreapp.listas.DetailFragment
import org.bedu.modastoreapp.listas.ListFragment
import org.bedu.modastoreapp.modelos.Order
import org.bedu.modastoreapp.modelos.Product
import org.bedu.modastoreapp.modelos.RegisteredUser

class CartActivity : AppCompatActivity() {

    private lateinit var menu_bar : BottomNavigationView
    private lateinit var button_pay : Button
    private lateinit var total_price : TextView


    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    companion object {
        const val CHANNEL_CLIENT = "CHANNEL_COURSES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotificationChannel()
        }

        menu_bar = findViewById(R.id.cart_menu)
        button_pay = findViewById(R.id.cart_btn_shop)
        total_price = findViewById(R.id.cart_txt_total)

        val username = "tomas11"
        val regUser = MYSTORE.getUserName(username)

        if (regUser != null) {
            total_price.text = "$ ${regUser.getTotal()}"
        }

        button_pay.setOnClickListener{
            shrink(button_pay)
            if (regUser != null) {
                confirmDialog(regUser)
            }
        }


        val listFragment = supportFragmentManager.findFragmentById(R.id.cart_fragment_container) as ListFragment
        if (username != null) {
            listFragment.setUsername(username)
        } else {
            listFragment.setUsername("tomas11")
        }

        listFragment.setListener{
            val detailFragment = supportFragmentManager.findFragmentById(R.id.fragmentDetail) as? DetailFragment

            if(detailFragment!=null){
                detailFragment.showProduct(it)
            } else{
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.PRODUCT,it)
                startActivity(intent)
            }
        }

        menu_bar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, username)

                    val intent = Intent(this, HomeActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)
                }
                R.id.menu_community -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, username)

                    val intent = Intent(this, CommunityActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)

                }
                R.id.menu_profile -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, username)

                    val intent = Intent(this, ProfileActivity::class.java).apply {
                        putExtras(bundle)
                    }

                    startActivity(intent)
                }
            }
            true
        }
    }

    private fun confirmDialog(regUser: RegisteredUser) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Pago")
        builder.setMessage("¿Deseas pagar un total de ${regUser.getTotal()} por tu compra?")

        builder.setNegativeButton(
            "Cancelar"
        ) { dialog, which -> }

        builder.setPositiveButton(
            "ok"
        ) { dialogInterface, i ->
            val order = Order(regUser.getOrders().size,regUser.getShoppingCart(),regUser.getTotal(),regUser.getAddress())
            regUser.addOrder(order)
            regUser.setShoppingCart(mutableListOf<Product>())
            Toast.makeText(applicationContext, "Gracias por tu compra ID: ${order.id}", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()

            touchNotification()
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

        val channel = NotificationChannel(CHANNEL_CLIENT, name, importance).apply {
            description = descriptionText
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }

    private fun touchNotification(){
        val intent = Intent(this,HomeActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification = NotificationCompat.Builder(this, CHANNEL_CLIENT)
            .setSmallIcon(R.drawable.ic_logo)
            .setColor(ContextCompat.getColor(this,R.color.blue))
            .setContentTitle(getString(R.string.action_title))
            .setContentText(getString(R.string.action_body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(this).run{
            notify(20,notification)
        }
    }
}