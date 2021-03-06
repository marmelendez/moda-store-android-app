/**
 * Data class Order
 *
 * Contains the properties of an Order
 */
package org.bedu.modastoreapp.modelos

data class Order(
    val id: Int,
    val products: List<Product>,
    val total: Float,
    val address: String)