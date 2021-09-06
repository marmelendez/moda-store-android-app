package org.bedu.modastoreapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.Slide
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.ibrahimsn.lib.SmoothBottomBar
import org.bedu.modastoreapp.listas.ShopContainer
import org.bedu.modastoreapp.listas.ShopContainerAdapter
import java.util.ArrayList

class HomeActivity : AppCompatActivity() {

    override fun onCreate(saveWomenInstanceState: Bundle?) {
        super.onCreate(saveWomenInstanceState)
        setContentView(R.layout.activity_shop)

        val menu_bar = findViewById<BottomNavigationView>(R.id.home_menu)
        val input_search = findViewById<EditText>(R.id.home_search)
        val username = "tomas11"

        val transition = Slide(Gravity.TOP).apply {
            duration = 500
            excludeTarget(window.decorView.findViewById<View>(R.id.action_bar_container), true)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }

        window.enterTransition = transition

        menu_bar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_shopping_cart -> {
                    val bundle = Bundle()
                    bundle.putString(USERNAME, username)

                    val intent = Intent(this, CartActivity::class.java).apply {
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

        input_search.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(USERNAME, username)

            val intent = Intent(this, SearchProductActivity::class.java).apply {
                putExtras(bundle)
            }

            startActivity(intent)
        }

        val products = MYSTORE.catalogProduct

        val womenViewPager = findViewById<ViewPager2>(R.id.WomenSlider)
        val sliderWomen: MutableList<ShopContainer> = ArrayList()

        for (i in 1..5) {
            val sliderWomenv = ShopContainer()
            sliderWomenv.image = products[i].image
            sliderWomenv.title = products[i].name
            sliderWomenv.location = products[i].price
            sliderWomen.add(sliderWomenv)
        }

        womenViewPager.adapter = ShopContainerAdapter(sliderWomen)
        womenViewPager.clipToPadding = false
        womenViewPager.clipChildren = false
        womenViewPager.offscreenPageLimit = 3
        womenViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositeWomenPageTransformer = CompositePageTransformer()
        compositeWomenPageTransformer.addTransformer(MarginPageTransformer(40))
        compositeWomenPageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.95f + r * 0.05f
        }
        womenViewPager.setPageTransformer(compositeWomenPageTransformer)

        val menViewPager = findViewById<ViewPager2>(R.id.MenSlider)
        val sliderMEN: MutableList<ShopContainer> = ArrayList()

        for (i in 11..15) {
            val sliderMenv1 = ShopContainer()
            sliderMenv1.image = products[i].image
            sliderMenv1.title = products[i].name
            sliderMenv1.location = products[i].price
            sliderMEN.add(sliderMenv1)
        }

        menViewPager.adapter = ShopContainerAdapter(sliderMEN)
        menViewPager.clipToPadding = false
        menViewPager.clipChildren = false
        menViewPager.offscreenPageLimit = 3
        menViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositeMenPageTransformer = CompositePageTransformer()
        compositeMenPageTransformer.addTransformer(MarginPageTransformer(40))
        compositeMenPageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.95f + r * 0.05f
        }
        menViewPager.setPageTransformer(compositeMenPageTransformer)


        val kidViewPager = findViewById<ViewPager2>(R.id.KidSlider)
        val sliderKid: MutableList<ShopContainer> = ArrayList()

        val sliderKidv1 = ShopContainer()
        sliderKidv1.image = R.drawable.image_n1
        sliderKidv1.title = "Chamarra negra "
        sliderKidv1.location = 400.0F
        sliderKid.add(sliderKidv1)

        val sliderKidv2 = ShopContainer()
        sliderKidv2.image = R.drawable.image_n2
        sliderKidv2.title = "Gorra naranja"
        sliderKidv2.location = 100.0F
        sliderKid.add(sliderKidv2)

        val sliderKidv3 = ShopContainer()
        sliderKidv3.image = R.drawable.image_n3
        sliderKidv3.title = "Chaleco azul"
        sliderKidv3.location = 200.0F
        sliderKid.add(sliderKidv3)

        val sliderKidv4 = ShopContainer()
        sliderKidv4.image = R.drawable.image_n4
        sliderKidv4.title = "Chamarra gris"
        sliderKidv4.location = 300.0F
        sliderKid.add(sliderKidv4)

        kidViewPager.adapter = ShopContainerAdapter(sliderKid)
        kidViewPager.clipToPadding = false
        kidViewPager.clipChildren = false
        kidViewPager.offscreenPageLimit = 3
        kidViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val compositeKidPageTransformer = CompositePageTransformer()
        compositeKidPageTransformer.addTransformer(MarginPageTransformer(40))
        compositeKidPageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.95f + r * 0.05f
        }
        kidViewPager.setPageTransformer(compositeKidPageTransformer)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.navigation_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

