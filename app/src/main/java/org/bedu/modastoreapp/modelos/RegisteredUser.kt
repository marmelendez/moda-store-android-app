/**
 * A registered user of the Store
 *
 * Implements the methods of a registered user in the app
 *
 * @property idUser Unique ID
 * @property name username
 * @property email email registered
 * @property password password of the account
 */
package org.bedu.modastoreapp.modelos

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RegisteredUser(
    override val idUser: String,
    private var name: String,
    private var email: String,
    private var password: String): User(idUser){

    private var address = ""
    private var shoppingCart = mutableListOf<Product>()
    private var favorites = mutableListOf<Product>()
    private var orders = mutableListOf<Order>()
    private var paymentMethod: MutableMap<String,Map<String, String>> =
        mutableMapOf("Credit card" to mapOf(), "Debit card" to mapOf())


    /**
     * Get the username
     * @return String name
     * */
    fun getName(): String {
        return this.name
    }

    /**
     * Get the email
     * @return String email
     * */
    fun getEmail(): String {
        return this.email
    }


    /**
     * Get the password
     * @return String password
     * */
    internal fun getPassword(): String {
        return this.password
    }

    /*
    /**
     * Set a new value to the name property
     * @param name new value of username
     * */
    private fun setName(name: String) {
        this.name = name
    }

    /**
     * Set a new value to the email property
     * @param email new value of email
     * */
    private fun setEmail(email:String) {
        this.email = email
    }

    /**
     * Set a new value to the password property
     * @param password new value of password
     * */
    private fun setPassword(password:String) {
        this.password= password
    }

    /**
     * Set a new value to the address property
     * @param address new value of address
     * */
    fun setAddress(address: String) {
        this.address = address
    }

    /**
     * Set a new value to the payment method property
     * @param type type of payment method (credit or debit card)
     * @param data information of the card
     * */
    fun setPaymentMethod(type: String, data: Map<String,String>) {
        this.paymentMethod[type] = data
    }

    /**
     * Find a product with a specific id
     * @param list the list of products
     * @param text the name of the list
     * @return Product if there's one with the given id, null if there's not
     * */
    private fun findProduct(list: MutableList<Product>, text: String): Product? {
        print("   -> Please enter the product ID: ")
        val id = readLine().toString()
        val selectedProduct = list.filter { id == it.idProduct.toString() }
        return try {
            selectedProduct[0]
        } catch (e: Exception) {
            println("Sorry, couldn't find a product with the id $id in your $text :(")
            null
        }
    }

    /**
     * Show the list of favorites and a menu
     * */
    fun displayFavorites() {
        println("\n---------- MODA Store | FAVORITES----------")
        println("\tID \tProduct name \tPrice")
        if (this.favorites.isEmpty()) {
            println("Your list of favorites is empty :( \n\nPress ENTER and return to menu to find some cool products")
        } else {
            this.favorites.forEach { println("\t${it.idProduct} \t${it.name} \t${it.price}")}
            print(
                "\nDo you want to ...?" +
                        "\n  1) Add product to cart" +
                        "\n  2) Remove a product" +
                        "\n  3) Return to menu please" +
                        "\n\n-> Choose an option: ")
            when (readLine().toString()) {
                "1" -> addProductToCart()
                "2" -> removeProductFromFavorites()
                "3" -> println("Returning to menu, press ENTER")
            }
        }
        readLine()
    }

    /**
     * Add a product from the list of favorites to the cart
     * */
    private fun addProductToCart() {
        val product = findProduct(this.favorites, "favorites")
        if (product != null) {
            addToCart(product)
        }
    }

    /**
     * Remove a product that is in the list of favorites
     * */
    private fun removeProductFromFavorites() {
        val product = findProduct(this.favorites, "favorites")
        if (product != null) {
            print("Are you sure you want to remove the product ${product.name} from your list of favorites? y/n ")
            if (readLine().toString() == "y") removeFromFavorites(product)
            else println("The product ${product.name} is still on your list")
        }
    }

    /**
     * Show the products and price of the shopping cart
     * @param store the app store
     * */
    suspend fun displayShoppingCart(store: Store) {
        println("\n---------- MODA Store | SHOPPING CART----------")
        println("\tID \tProduct name \tPrice")
        if (this.shoppingCart.isEmpty()) {
            println("Your cart is empty :( \n\nPress ENTER and return to the menu to find some cool products")
        } else {
            this.shoppingCart.forEach { println("\t${it.idProduct} \t${it.name} \t${it.price}") }
            print(
                "\nDo you want to ...?" +
                        "\n  1) Proceed to payment" +
                        "\n  2) Remove a product" +
                        "\n  3) Add a product " +
                        "\n  4) Return to menu please" +
                        "\n\n-> Choose an option: ")
            when (readLine().toString()) {
                "1" -> makePurchase()
                "2" -> removeProductFromCart()
                "3" -> searchProduct(store, this)
                "4" -> println("Returning to menu, press ENTER")
            }
        }
        readLine()
    }

    /**
     * Ask for the missing data and realize the purchase with the product from the shopping cart
     * */
    private suspend fun makePurchase() {
        println("\n---------- MODA Store | PAYMENT ----------")
        val total = getTotal()
        askAddress()
        val type = askPaymentMethod()
        var ask =  true
        if (paymentMethod.getValue(type).values.isNotEmpty()) ask = false
        checkPaymentMethod(type)
        println()

        addOrder(Order(this.orders.size.toString(), this.shoppingCart,total,this.address, this.paymentMethod, LocalDateTime.now()))

        if (ask) {
            print("Do you want to save your payment method for future purchases? y/n: ")
            if (readLine().toString() == "y") {
                println ("Payment method saved")
            } else {
                this.paymentMethod[type] = mapOf()
                println ("The payment method wasn't saved for future purchases")
            }
        }
        this.shoppingCart = mutableListOf()
    }

    /**
     * Add the prices of each product in the shopping cart
     * @return Float the total
     * */
    private fun getTotal(): Float {
        var subtotal = 0F
        shoppingCart.forEach {
            println(" - ${it.name}\t $ ${it.price}")
            subtotal += it.price
        }
        val iva = subtotal * 0.16F
        val total = subtotal + iva
        println("Subtotal: $ ${subtotal}\nIVA: ${iva}\nTotal: $total")
        return total
    }

    /**
     * If there's a saved address asked if the user want to use it for the purchase
     * If not get the address property value again
     * */
    private fun askAddress() {
        var res: String
        if (this.address.isNotEmpty()) {
            print("\n! You have the following saved address ${this.address} do you want to use it? y/n: ")
            res = readLine().toString()
            while (res != "y" && res != "n") {
                print("\n--> please enter \"y\" for yes or \"n\" for no: ")
                res = readLine().toString()
            }
            if (res == "n") this.address = ""
        }
        while (this.address.isEmpty()) {
            println("\n! Please enter your address")
            this.address = fillAddressData()
        }
    }

    /**
     * Get the address property value
     * */
    private fun fillAddressData(): String {
        var userAddress = ""
        print("Street: ")
        userAddress += readLine().toString() + " "
        print("Nº: ")
        userAddress += readLine().toString() + " "
        print("Country: ")
        userAddress += readLine().toString() + ", "
        print("State: ")
        userAddress += readLine().toString() + ", "
        print("City: ")
        userAddress += readLine().toString() + ", "
        print("Zip code: ")
        userAddress += readLine().toString()

        return userAddress
    }

    /**
     * Ask for the type of payment method: credit or debit card
     * @return String the type: credit or debit card
     * */
    private fun askPaymentMethod(): String {
        print("\nSelect your payment method" +
                "\n  1) Credit card" +
                "\n  2) Debit card" +
                "\n-> Choose an option: ")
        var type = readLine().toString()
        var flag = true

        while (flag) {
            when (type) {
                "1" -> {
                    type = "Credit card"
                    flag = false
                }
                "2" -> {
                    type = "Debit card"
                    flag = false
                }
                else -> {
                    print("\n--> please enter \"1\" for Credit Card or \"2\" for Debit Card: ")
                    type = readLine().toString()
                }
            }
        }
        return type
    }

    /**
     * Check if there's a saved payment method
     * If not ask for the information
     * @param type credit or debit card
     * */
    private fun checkPaymentMethod(type: String) {
        if (this.paymentMethod.getValue(type).values.isEmpty()) {
            println("\n! Please enter your $type data")
            fillCardData(type)
        } else {
            val num = this.paymentMethod[type]?.getValue("Number")
            print("\n! You have a $type saved, with the number $num do you want to use it? y/n: ")
            var res = readLine().toString()
            while (res != "y" && res != "n") {
                print("\n--> please enter \"y\" for yes or \"n\" for no: ")
                res = readLine().toString()
            }
            if (res == "n"){
                println("Then please enter your $type data")
                fillCardData(type)
            } else {
                println("Okay, let's proceed! :)")
            }
        }
    }

    /**
     * Get and set the payment method property value
     * @param type credit or debit card
     * */
    private fun fillCardData(type: String) {
        print("Number (16 digits): ")
        var number = readLine().toString()
        number = getValidData(number, 15, "Enter a valid number, must have 16 digits: ")

        print("Date MM/YY: ")
        var date = readLine().toString()
        date = getValidData(date, 5,"Enter a valid date: ")

        print("Security number (3 digits): ")
        var securityNum = readLine().toString()
        securityNum = getValidData(securityNum, 3, "Enter a valid security number, must have 3 digits: ")

        val data = mapOf("Number" to number, "Date" to date, "Security number" to securityNum )
        this.paymentMethod[type] = data
    }

    /**
     * Evaluate if the credit card data is valid
     * @param response users input
     * @param cond the condition with which it must meet
     * @param message an error message
     * @return String a valid data
     * */
    private fun getValidData(response: String, cond: Int, message: String): String{
        var data = response
        while (data.length < cond) {
            print("--- $message")
            data = readLine().toString()
        }
        return data
    }

    /**
     * Remove a product from de shopping cart
     * */
    private fun removeProductFromCart() {
        val product = findProduct(this.shoppingCart, "cart")
        if (product != null) {
            print("Are you sure you want to remove the product ${product.name} from your cart? y/n ")
            if (readLine().toString() == "y") removeFromCart(product)
            else println("The product ${product.name} is still on your cart")
        }
    }

    /**
     * Display all the orders made
     * */
    fun displayOrders() {
        println("\n---------- MODA Store | ORDERS ----------")
        if (this.orders.isEmpty()) {
            println("Your list of orders is empty :( \n\nPress ENTER and return to menu to find some cool products")
        } else {
            this.orders.forEach { it ->
                println("-----------------------------------------" +
                        "\n ID: ${it.id}" +
                        "\n Products:")
                it.products.forEach { println("  - \t${it.name}   \t$${it.price}") }
                println(" Total: $ ${it.total}" +
                        "\n Address: ${it.address}" +
                        "\n Date: ${it.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}" +
                        "\n-----------------------------------------")
            }
        }
        println("Press ENTER to return to menu")
        readLine()
    }

    /**
     * Ask for the username and password of the user
     * @param store the app store
     * @return RegisteredUser the user with the given username and password
     * */
    suspend fun logIn(store: Store) : RegisteredUser? { //Boolean
        print("\n---------- MODA Store | LOG IN ----------\nUsername: ")
        var name = readLine().toString()

        while (store.getUser(name) == null) {
            print("Sorry the username $name is not registered. Try with another one: ")
            name = readLine().toString()
        }

        val regUser = store.getUser(name)
        print("Password: ")
        var password : String = readLine().toString()

        if (regUser != null) {
            while (regUser.getPassword() != password) {
                print("Incorrect password: ")
                password = readLine().toString()
            }
            println("\n Welcome again $name to MODA Store! :)")
        } else {
            println("\n ! Sorry couldn't find an account with the username $name")
        }

        readLine()
        return regUser
    }

    /**
     * Log out from the registered account
     * */
    fun logOut() : Boolean {
        print("\n---------- MODA Store | LOG OUT ----------")
        return true
    }

    /**
     * Displays the information of the payment method
     * @param type credit or debit card
     * */
    private fun printPaymentData(type: String){
        println("\t-> $type")
        this.paymentMethod.getValue(type).forEach{
            println("\t\t${it.key} \t${it.value}")
        }
    }

    /**
     * Displays the profile information and the menu
     * @param store the app store
     * */
    fun profile(store: Store){
        println("\n---------- MODA Store | PROFILE ----------")
        println("Welcome again ${this.name} to MODA Store")

        println("-----------------------------------------\nProfile information" +
                "\n\tID: ${this.idUser}" +
                "\n\tNombre: ${this.name}" +
                "\n\tEmail: ${this.email}"+
                "\n\tAddress: ${this.address}"+
                "\n\tPayment Method:")
        printPaymentData("Credit card")
        printPaymentData("Debit card")

        if(this.address == ""
            || this.paymentMethod.getValue("Credit card").values.isEmpty()
            || this.paymentMethod.getValue("Debit card").values.isEmpty()) println("! Some lands are blanked, Consider to fill it")

        println("-----------------------------------------")
        var flag = true
        while(flag){
            print("\nWhat would you like to do?" +
                    "\n1) Change settings" +
                    "\n2) Add information" +
                    "\n3) Return to menu" +
                    "\n\n-> Choose an option: ")
            when (readLine().toString()) {
                "1" -> {
                    changeSettings()
                    profile(store)
                    flag = false
                }
                "2" -> {
                    addInformation()
                    profile(store)
                    flag = false
                }
                "3" -> {
                    println("Returning to menu, press ENTER")
                    flag = false
                }
                else -> {
                    print("\nSorry, please select a valid option(1-2): ")
                }
            }
        }
    }

    /**
     * Display the properties the user is able to change and change it
     * */
    private fun changeSettings(){
        var flag = true
        while(flag){
            print("\nWhat would you like to change?" +
                    "\n1) Name" +
                    "\n2) Email" +
                    "\n3) Password" +
                    "\n4) Address" +
                    "\n5) Payment Method" +
                    "\n6) Return to profile" +
                    "\n\n-> Choose an option: ")
            when (readLine().toString()) {
                "1" -> {
                    setName(askUsername(myStore))
                    println("Your name has been changed to ${this.name}")
                }
                "2" -> {
                    setEmail(askEmail(myStore))
                    println("Your email has been changed to ${this.email}")
                }
                "3" -> {
                    setPassword(askPassword())
                    println("Your password has been changed to ${this.password}")
                }
                "4" -> setAddress(fillAddressData())
                "5" -> {
                    if (askPaymentMethod() == "Credit card"){
                        println("------------------------------------\nYour credit card")
                        fillCardData("Credit card")
                    } else {
                        println("------------------------------------\nYour debit card")
                        fillCardData("Debit card")
                    }
                }
                "6" -> flag = false
                else -> {
                    println("Sorry, please select a valid option(1-6)\n")
                }
            }
        }
    }

    /**
     * Displays the menu to add information (address of payment method)
     * */
    private fun addInformation(){
        var flag = true
        while(flag){
            print("\nWhat type of information would you like to add?" +
                    "\n1) Add address" +
                    "\n2) Add payment method" +
                    "\n3) Return to profile" +
                    "\n\n-> Choose an option: ")
            when (readLine().toString()) {
                "1" -> setAddress(fillAddressData())
                "2" -> {
                    println("------------------------------------\nYour Credit card")
                    fillCardData("Credit card")
                    println("------------------------------------\nYour Debit card")
                    fillCardData("Debit card")
                }
                "3" -> flag = false
                else -> {
                    println("Sorry, please select a valid option(1-3)\n")
                }
            }
        }
    }

    /**
     * Add a product to the shopping cart
     * @param product the product to add
     * */
    fun addToCart(product: Product) {
        println("The product ${product.name} has been added to your cart")
        this.shoppingCart.add(product)
    }

    /**
     * Remove a product from the shopping cart
     * @param product the product to remove
     * */
    private fun removeFromCart(product: Product) {
        println("The product ${product.name} has been removed to your cart")
        this.shoppingCart.remove(product)
    }

    /**
     * Add a product to the favorite list
     * @param product the product to add
     * */
    fun addToFavorite(product: Product) {
        println("The product ${product.name} has been added to your favorite list")
        this.favorites.add(product)
    }

    /**
     * Remove a product from the favorite list
     * @param product the product to remove
     * */
    private fun removeFromFavorites(product: Product) {
        println("The product ${product.name} has been removed to your cart")
        this.favorites.remove(product)
    }

    /**
     * Add a product to the orders list
     * @param order the order to add
     * */
    private fun addOrder(order: Order) {
        println("The order ${order.id} with a total of $ ${order.total} has been completed!")
        this.orders.add(order)
    }*/
}