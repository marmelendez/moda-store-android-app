/**
 * Data class Product
 *
 * Contains the properties of a Product
 */
package org.bedu.modastoreapp.modelos

import org.bedu.modastoreapp.modelos.Category

data class Product(
    val idProduct: Int,
    var name: String,
    var category: Category,
    var color: String,
    var price: Float,
    var quantity: Map<String, Int>)

