/**
 * A general user of the Store
 *
 * Implements the methods of a general user in the app
 *
 * @property idUser Unique ID of the user
 */
package org.bedu.modastoreapp.modelos

import java.util.*

open class User (open val idUser: String) {
    /**
     * Search if a product is in the store catalog
     * @param store the app store
     * @param user the registered user
     * */
    fun searchProduct(store: Store, user: RegisteredUser? = null) {
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
                        "1" -> println("Returning to menu, press ENTER")//user.addToCart(product)
                        "2" -> println("Returning to menu, press ENTER")//user.addToFavorite(product)
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