/**
 * Data class Product
 *
 * Contains the properties of a Product
 */
package org.bedu.modastoreapp.modelos

import org.bedu.modastoreapp.modelos.Category

data class Product(
    val idProduct: Int,
    val name: String,
    val category: Category,
    val color: String,
    val price: Float,
    val image: Int,
    var quantity: Map<String, Int>)

