/**
 * Represents the Store in the app
 *
 * Implements the StoreInterface methods and use of properties. Also it implements new methods.
 *
 * @property name Name of the store
 */
package org.bedu.modastoreapp.modelos

import modelos.RegisteredUser
import java.util.*

class Store (override val name: String): StoreInterface {
    override val catalogProduct: MutableList<Product> = mutableListOf()
    override val catalogCategory: MutableList<Category> = mutableListOf()
    override val listOfUsers: MutableList<RegisteredUser> = mutableListOf()

    /**
     * Add a new Category to the catalog product
     * @param category the new category
     * */
    override fun addCategory(category: Category) {
        this.catalogCategory.add(category)
    }

    /**
     * Add a new Product to the product product
     * @param product the new product
     * */
    override fun addProduct(product: Product) {
        this.catalogProduct.add(product)
    }

    /**
     * Add a new User to the list of users
     * @param user the new user
     * */
    override fun addUser(user: RegisteredUser) {
        this.listOfUsers.add(user)
    }

    /**
     * Check if the username is already in the list of user's username
     * @param username the username we want to check
     * @return Boolean True if is already in the list, false if not
     * */
    fun isInListOfUsersUsername(username: String): Boolean {
        // none: returns 'true' if the collection has no elements.
        return this.listOfUsers.none { it.getName().lowercase(Locale.getDefault()).contains(username.lowercase(Locale.getDefault())) }
    }

    /**
     * Check if the email is already in the list of user's email
     * @param email the email we want to check
     * @return Boolean True if is already in the list, false if not
     * */
    fun isInListOfUsersEmail(email: String): Boolean {
        return this.listOfUsers.none { it.getEmail().lowercase(Locale.getDefault()).contains(email.lowercase(Locale.getDefault())) }
    }

    /**
     * Get the Registered User that has a given username
     * @param username the username of the Registered User
     * @return RegisteredUser If the username doesn't correspond to a RegisteredUser returns null
     * */
    fun getUser(username: String): RegisteredUser? {
        val possibleUser = this.listOfUsers.filter{ it.getName() == username }
        return try {
            possibleUser[0]
        } catch(e: Exception) {
            null
        }
    }
}