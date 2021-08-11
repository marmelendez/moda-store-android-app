/**
 * Represents the Store in the app
 *
 * Implements the StoreInterface methods and use of properties. Also it implements new methods.
 *
 * @property name Name of the store
 */
package org.bedu.modastoreapp.modelos

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.*

class Store(var name: String?) : Serializable, Parcelable {
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
     * Add a new Product to the product product
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
     * @param id the id of the Registered User
     * @return RegisteredUser If the id user doesn't correspond to a RegisteredUser returns null
     * */
    fun getUser(id: Int?): RegisteredUser? {
        val possibleUser = this.listOfUsers.filter{ it.idUser == id }
        return try {
            possibleUser[0]
        } catch(e: Exception) {
            null
        }
    }

    /**
     * Get the Registered User that has a given username
     * @param id the id of the Registered User
     * @return RegisteredUser If the id user doesn't correspond to a RegisteredUser returns null
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
     * Search if a product is in the store catalog
     * @param productName the product name the user wants to search
     * @return List<Product> list of products found
     * */
    fun searchProduct(productName : String) : List<Product> {
        val result = this.catalogProduct.filter { it.name.lowercase(Locale.getDefault()).contains(productName.lowercase(Locale.getDefault())) }
        return result//.forEach { println("\t${it.idProduct} \t${it.name}") }
    }

    constructor(parcel: Parcel) : this(parcel.readString()) {
        this.name = parcel.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Store> {
        override fun createFromParcel(parcel: Parcel): Store {
            return Store(parcel)
        }

        override fun newArray(size: Int): Array<Store?> {
            return arrayOfNulls(size)
        }
    }
}