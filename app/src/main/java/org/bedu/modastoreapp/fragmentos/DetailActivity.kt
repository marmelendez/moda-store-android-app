package org.bedu.modastoreapp.fragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.bedu.modastoreapp.R
import org.bedu.modastoreapp.listas.DetailFragment
import org.bedu.modastoreapp.modelos.ProductPar

class DetailActivity : AppCompatActivity() {

    companion object {
        val PRODUCT = "PRODUCT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val product = intent.getParcelableExtra<ProductPar>(PRODUCT)
        val detailFragment = supportFragmentManager.findFragmentById(R.id.fragmentDetail) as? DetailFragment
        if (product != null) {
            detailFragment?.showProduct(product)
        }
    }
}
