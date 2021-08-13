/**
 * Represents the Store in the app
 *
 * Implements the StoreInterface methods and use of properties. Also it implements new methods.
 *
 * @property name Name of the store
 */
package org.bedu.modastoreapp.modelos

import java.util.*

class Store(var name: String?) {
    val catalogProduct: MutableList<Product> = mutableListOf()
    val catalogCategory: MutableList<Category> = mutableListOf()
    val listOfUsers: MutableList<RegisteredUser> = mutableListOf()

    /**
     * Add a new Category to the catalog product
     * @param category the new category
     * */
    fun addCategory(category: Category) {
        this.catalogCategory.add(category)
    }

    /**
     * Add a new Product to the catalog product
     * @param product the new product
     * */
    fun addProduct(product: Product) {
        this.catalogProduct.add(product)
    }

    /**
     * Add a new User to the list of users
     * @param user the new user
     * */
    fun addUser(user: RegisteredUser) {
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
     * @return RegisteredUser If the username user doesn't correspond to a RegisteredUser returns null
     * */
    fun getUserName(username: String): RegisteredUser? {
        val possibleUser = this.listOfUsers.filter{ it.getName() == username }
        return try {
            possibleUser[0]
        } catch(e: Exception) {
            null
        }
    }

    /**
     * Get the Product that has a given id
     * @param id the id of the Product
     * @return RegisteredUser If the id user doesn't correspond to a Product returns null
     * */
    fun getProduct(id: Int?): Product? {
        val product = this.catalogProduct.filter{ it.idProduct == id }
        return try {
            product[0]
        } catch(e: Exception) {
            null
        }
    }

    /**
     * Search if a product is in the store catalog
     * @param productName the product name the user wants to search
     * @return List<Product> list of products found
     * */
    fun searchProduct(productName : String) : List<Product> {
        val result = this.catalogProduct.filter { it.name.lowercase(Locale.getDefault()).contains(productName.lowercase(Locale.getDefault())) }
        return result
    }
}