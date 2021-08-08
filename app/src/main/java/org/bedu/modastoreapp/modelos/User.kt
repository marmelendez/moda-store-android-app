/**
 * A general user of the Store
 *
 * Implements the methods of a general user in the app
 *
 * @property idUser Unique ID of the user
 */
package org.bedu.modastoreapp.modelos

import modelos.RegisteredUser
import java.util.*

open class User (open val idUser: String) {

    /**
     * Register a new user
     * @param store the app store
     * @return RegisteredUser the new registered user
     * */
    suspend fun signIn(store: Store): RegisteredUser {
        println("\n---------- MODA Store | SIGN IN ----------")

        val name = askUsername(store)
        val email = askEmail(store)
        val password = askPassword()

        val newUser = RegisteredUser(store.listOfUsers.size.toString(), name, email, password)
        store.addUser(newUser)

        println("\nWelcome ${newUser.getName()}! now you have an account")

        readLine()
        return newUser
    }

    /**
     * Ask for the username and validates it
     * @param store the app store
     * @return String a valid username
     * */
    internal fun askUsername(store: Store): String {
        print("-> Enter your username: ")
        var name = readLine().toString()
        val error1 = "--- Enter a valid username (start with a letter, can contain letters and numbers and at least 6 characters): "
        val error2 = "--- The given username is already registered: "
        while (!validate(error1, Form.validateUsername(name))
            || !validate(error2, store.isInListOfUsersUsername(name))) {
            name = readLine().toString()
        }
        return name
    }

    /**
     * Ask for the email and validates it
     * @param store the app store
     * @return String a valid email
     * */
    internal fun askEmail(store: Store): String {
        print("-> Enter your email: ")
        var email = readLine().toString()
        val error1 = "--- Enter a valid email (ends with @domain.com): "
        val error2 = "--- The given email is already registered: "
        while (!validate(error1, Form.validateEmail(email))
            || !validate(error2, store.isInListOfUsersEmail(email))) {
            email = readLine().toString()
        }
        return email
    }

    /**
     * Ask for the password and validates it
     * @return String a valid password
     * */
    internal fun askPassword(): String {
        print("-> Enter your password, we recommend you this one ${Form.passwordGenerator()}: ")
        var password = readLine().toString()
        val error1 = "--- Enter a valid password (minimum length: 8,can contain letters, numbers or . / _): "
        while (!validate(error1, Form.validatePassword(password))) {
            password = readLine().toString()
        }
        return password
    }

    /**
     * Validate the data with the given function
     * @param error the error message to print
     * @param funValidate the function we're going to use to validate the data
     * @return Boolean false if there's and error, true if there's not
     * */
    private fun validate(error: String, funValidate: Boolean): Boolean {
        if (!funValidate) {
            print(error)
            return false
        }
        return true
    }

    /**
     * Search if a product is in the store catalog
     * @param store the app store
     * @param user the registered user
     * */
    suspend fun searchProduct(store: Store, user: RegisteredUser? = null) {
        var flag = true
        var option: String

        while (flag) {
            print("\n---------- MODA Store | SEARCH ----------\n-> Hi there, which product are you looking for? ")

            val productName = readLine().toString()
            val result = store.catalogProduct.filter { it.name.lowercase(Locale.getDefault()).contains(productName.lowercase(Locale.getDefault())) }

            val text = if (result.isNotEmpty()) " We found ${result.size} results :)" else " Sorry no match found :("
            println("${text}\n\tID \tName")
            result.forEach { println("\t${it.idProduct} \t${it.name}") }
            print(
                "\nDo you want to ...?" +
                        "\n  1) Search another product" +
                        "\n  2) Select a product " +
                        "\n  3) Return to menu please" +
                        "\n\n-> Choose an option: ")
            option = readLine().toString()
            flag = when (option) {
                "1" -> true
                "2" -> {
                    selectProduct(store, user)
                    false
                }
                "3" -> {
                    println("Returning to menu, press ENTER")
                    false
                }
                else -> false
            }
        }
        readLine()
    }

    /**
     * Select a product from the store catalog
     * @param store the app store
     * @param user the registered user
     * */
    private fun selectProduct(store: Store, user: RegisteredUser?) {
        print("   -> Please enter the product ID: ")
        val id = readLine().toString()
        val selectedProduct = store.catalogProduct.filter { id == it.idProduct.toString() }

        try {
            val product = selectedProduct[0]
            println("\n---------- MODA Store | ${product.name} ----------" +
                    "\nID: ${product.idProduct}" +
                    "\nPrice: ${product.price}" +
                    "\nColor: ${product.color}" +
                    "\nCategory: ${product.category.name}" +
                    "\nSize: ${product.quantity.map { it.key }}"
            )
            print(
                "\nDo you want to ...?" +
                        "\n  1) Add to cart" +
                        "\n  2) Add to favorites " +
                        "\n  3) Return to menu please" +
                        "\n\n-> Choose an option: "
            )
            val op = readLine().toString()
            if (op == "1" || op == "2") {
                if(user != null){
                    when (op){
                        "1" -> user.addToCart(product)
                        "2" -> user.addToFavorite(product)
                        "3" -> println("Returning to menu, press ENTER")
                    }
                } else {
                    println("You don't have access to this part, please sign in or log in")
                }
            }
        } catch (e: Exception) {
            println("Sorry, couldn't find a product with the $id id :(")
        }
    }
}