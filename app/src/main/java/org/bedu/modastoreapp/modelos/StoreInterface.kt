/**
 * Interface of Store
 *
 * Declare the properties and methods of a Store
 */
package org.bedu.modastoreapp.modelos

import modelos.RegisteredUser

interface StoreInterface {
    val name: String
    val catalogProduct: MutableList<Product>
    val catalogCategory: MutableList<Category>
    val listOfUsers: MutableList<RegisteredUser>

    fun addCategory(category: Category)

    fun addProduct(product: Product)

    fun addUser(user: RegisteredUser)
}