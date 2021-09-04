package org.bedu.modastoreapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.bedu.modastoreapp.modelos.BaseDatos

val STORE = BaseDatos.start()

class HomeActivity : AppCompatActivity() {

    private lateinit var menu_bar : BottomNavigationView
    private lateinit var input_search : EditText
    private lateinit var fragment_inicio : FragmentHome
    private lateinit var fragment_search : FragmentSearchList

    override fun onCreate(saveWomenInstanceState: Bundle?) {
        super.onCreate(saveWomenInstanceState)
        setContentView(R.layout.activity_shop)

        fragment_inicio = FragmentHome()
        fragment_search = FragmentSearchList()

        menu_bar = findViewById(R.id.home_menu)
        input_search = findViewById(R.id.home_search)
        setFragment(fragment_inicio)

        menu_bar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    setFragment(fragment_inicio)
                    Toast.makeText(this, "ESTAS EN INICIO", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_shopping_cart -> {
                    Toast.makeText(this, "CARRITO", Toast.LENGTH_SHORT).show()
                    //val intent = Intent(this, CartActivity::class.java)
                    //startActivity(intent)
                }
                R.id.menu_profile -> {
                    Toast.makeText(this, "PERFIL", Toast.LENGTH_SHORT).show()
                    //val intent = Intent(this, ProfileActivity::class.java)
                    //startActivity(intent)
                }
            }
            true
        }

        input_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                setFragment(fragment_search)
                Toast.makeText(this@HomeActivity, "ESTAS EN BUSQUEDA", Toast.LENGTH_SHORT).show()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    fun setFragment(fragment: Fragment) {
        val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG_FRAGMENTO)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if(fragmentoActual != null){
            fragmentTransaction.remove(fragmentoActual)
        }
        fragmentTransaction.add(R.id.home_fragment_container, fragment, TAG_FRAGMENTO)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.navigation_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        private const val TAG_FRAGMENTO = "fragmento_inicio"
    }
}

