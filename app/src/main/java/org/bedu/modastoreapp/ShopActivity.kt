package org.bedu.modastoreapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import java.util.ArrayList

class ShopActivity : AppCompatActivity() {

    override fun onCreate(saveWomenInstanceState: Bundle?) {
        super.onCreate(saveWomenInstanceState)
        setContentView(R.layout.activity_shop)

        val womenViewPager = findViewById<ViewPager2>(R.id.WomenSlider)
        val sliderWomen: MutableList<ShopContainer> = ArrayList()

        val sliderWomenv1 = ShopContainer()
        sliderWomenv1.image = R.drawable.image_m1
        sliderWomenv1.title = "Blusa blanca"
        sliderWomenv1.location = "CDMX"
        sliderWomenv1.starRating = 4.8f
        sliderWomen.add(sliderWomenv1)

        val sliderWomenv2 = ShopContainer()
        sliderWomenv2.image = R.drawable.image_m2
        sliderWomenv2.title = "Sombrero negro"
        sliderWomenv2.location = "CDMX"
        sliderWomenv2.starRating = 4.5f
        sliderWomen.add(sliderWomenv2)

        val sliderWomenv3 = ShopContainer()
        sliderWomenv3.image = R.drawable.image_m3
        sliderWomenv3.title = "Blusa blanca"
        sliderWomenv3.location = "CDMX"
        sliderWomenv3.starRating = 4.3f
        sliderWomen.add(sliderWomenv3)

        val sliderWomenv4 = ShopContainer()
        sliderWomenv4.image = R.drawable.image_m4
        sliderWomenv4.title = "Blusa azul"
        sliderWomenv4.location = "CDMX"
        sliderWomenv4.starRating = 4.2f
        sliderWomen.add(sliderWomenv4)

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

        val sliderMenv1 = ShopContainer()
        sliderMenv1.image = R.drawable.image_h1
        sliderMenv1.title = "Chamarra negra "
        sliderMenv1.location = "CDMX"
        sliderMenv1.starRating = 4.8f
        sliderMEN.add(sliderMenv1)

        val sliderMenv2 = ShopContainer()
        sliderMenv2.image = R.drawable.image_h2
        sliderMenv2.title = "Gorra naranja"
        sliderMenv2.location = "CDMX"
        sliderMenv2.starRating = 4.5f
        sliderMEN.add(sliderMenv2)

        val sliderMenv3 = ShopContainer()
        sliderMenv3.image = R.drawable.image_h3
        sliderMenv3.title = "Chaleco azul"
        sliderMenv3.location = "CDMX"
        sliderMenv3.starRating = 4.3f
        sliderMEN.add(sliderMenv3)

        val sliderMenv4 = ShopContainer()
        sliderMenv4.image = R.drawable.image_h4
        sliderMenv4.title = "Chamarra gris"
        sliderMenv4.location = "CDMX"
        sliderMenv4.starRating = 4.2f
        sliderMEN.add(sliderMenv4)

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
        sliderKidv1.location = "CDMX"
        sliderKidv1.starRating = 4.8f
        sliderKid.add(sliderKidv1)

        val sliderKidv2 = ShopContainer()
        sliderKidv2.image = R.drawable.image_n2
        sliderKidv2.title = "Gorra naranja"
        sliderKidv2.location = "CDMX"
        sliderKidv2.starRating = 4.5f
        sliderKid.add(sliderKidv2)

        val sliderKidv3 = ShopContainer()
        sliderKidv3.image = R.drawable.image_n3
        sliderKidv3.title = "Chaleco azul"
        sliderKidv3.location = "CDMX"
        sliderKidv3.starRating = 4.3f
        sliderKid.add(sliderKidv3)

        val sliderKidv4 = ShopContainer()
        sliderKidv4.image = R.drawable.image_n4
        sliderKidv4.title = "Chamarra gris"
        sliderKidv4.location = "CDMX"
        sliderKidv4.starRating = 4.2f
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

}