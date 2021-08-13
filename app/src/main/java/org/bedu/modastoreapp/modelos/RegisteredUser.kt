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

class RegisteredUser (
    val idUser: Int,
    private var name: String,
    private var email: String,
    private var password: String) {

    private var address = ""
    private var debitCard = ""
    private var creditCard = ""
    private var shoppingCart = mutableListOf<Product>()
    private var favorites = mutableListOf<Product>()
    private var orders = mutableListOf<Order>()

    // GETTERS
    fun getName(): String {
        return this.name
    }

    fun getEmail(): String {
        return this.email
    }

    fun getPassword(): String {
        return this.password
    }

    fun getAddress(): String {
        return this.address
    }

    fun getDebitCard(): String {
        return this.debitCard
    }

    fun getCreditCard(): String {
        return this.creditCard
    }

    fun getShoppingCart(): MutableList<Product> {
        return this.shoppingCart
    }

    fun getOrders(): MutableList<Order> {
        return this.orders
    }

    fun getTotal(): Float {
        var total = 0F
        this.shoppingCart.forEach {
            total += it.price
        }
        return total
    }

    // SETTERS
    fun setName(name: String) {
        this.name = name
    }

    fun setEmail(email:String) {
        this.email = email
    }

    fun setPassword(password:String) {
        this.password= password
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun setCreditCard(creditCard: String) {
        this.creditCard = creditCard
    }

    fun setDebitCard(debitCard: String) {
        this.debitCard = debitCard
    }

    fun setShoppingCart(shoppingCart: MutableList<Product>) {
        this.shoppingCart = shoppingCart
    }

    /**
     * Add a product to the shopping cart
     * @param product the product to add
     * */
    fun addToCart(product: Product) {
        this.shoppingCart.add(product)
    }

    /**
     * Remove a product from the shopping cart
     * @param product the product to remove
     * */
    fun removeFromCart(product: Product) {
        this.shoppingCart.remove(product)
    }

    /**
     * Add a product to the favorite list
     * @param product the product to add
     * */
    fun addToFavorite(product: Product) {
        this.favorites.add(product)
    }

    /**
     * Remove a product from the favorite list
     * @param product the product to remove
     * */
    private fun removeFromFavorites(product: Product) {
        this.favorites.remove(product)
    }

    /**
     * Add a order to the orders list
     * @param order the order to add
     * */
    fun addOrder(order: Order) {
        this.orders.add(order)
    }
}