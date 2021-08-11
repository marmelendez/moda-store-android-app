/**
 * The default database of the programa
 *
 * Set some default configurations for Category, Product, User, Shopping Cart, Favorites, Address and Payment Method
 */
package org.bedu.modastoreapp.modelos

import org.bedu.modastoreapp.R

class BaseDatos {
    companion object Base {
        private val myStore = Store("MODA Store")

        private var idProduct = 1
        private var idUser = 1
        private var idCategory = 1

        fun start(): Store {
            setCategory()
            setProducts()
            setUsers()
            setShoppingCart()
            setFavorites()
            setAddress()
            setPaymentMethod()
            return myStore
        }

        private fun setCategory() {
            myStore.addCategory(Category(idCategory++,"Dama"))
            myStore.addCategory(Category(idCategory++,"Caballero"))
        }

        private fun setProducts() {
            val category = myStore.catalogCategory
            //Dama
            myStore.addProduct(Product(idProduct++,"Blusa estampada",category[0],"Blanca",130F, R.drawable.productm1, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Blusa de tirantes",category[0],"Blanco",300F, R.drawable.productm2, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Crop top con letras",category[0],"Gris",350F, R.drawable.productm3, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Vestido largo", category[0],"Verde",900F, R.drawable.productm4, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Vestido de fiesta",category[0],"Aqua",400F, R.drawable.productm5, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Short de mezclilla",category[0],"Azul claro",150F, R.drawable.productm6, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Falda de flores",category[0],"Rosa",200F, R.drawable.productm7, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Pantalon de mezclilla",category[0],"Azul",350F, R.drawable.productm8, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Pantalon formal",category[0],"Azul",400F, R.drawable.productm9, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Top con cuello",category[0],"Lila",150F, R.drawable.productm10, mapOf("S" to 100, "M" to 50, "X" to 100)))
            // Caballero
            myStore.addProduct(Product(idProduct++,"Sudadera",category[1],"Lila",250F, R.drawable.producth1, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Pantalon de mezclilla",category[1],"Azul",400F, R.drawable.producth2, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Camisa rayada",category[1],"Blanco",80F, R.drawable.producth3, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Pants",category[1],"Gris",120F, R.drawable.producth4, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Bermuda",category[1],"Caqui",250F, R.drawable.producth5,  mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Set pijama",category[1],"Gris/azul",250F, R.drawable.producth6, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Playera animado",category[1],"Beige",150F, R.drawable.producth7, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Jersey tejido",category[1],"Gris",180F, R.drawable.producth8 , mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Pantalon a medida",category[1],"Azul",320F, R.drawable.producth9, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Camisa vaquera",category[1],"Azul",180F, R.drawable.producth10,mapOf("S" to 100, "M" to 50, "X" to 100)))
        }


        private fun setUsers() {
            myStore.addUser(RegisteredUser(myStore.catalogProduct.size, "tomas11", "tomas@hotmail.com", "123"))
            myStore.addUser(RegisteredUser(myStore.catalogProduct.size, "didier32", "didier@hotmail.com", "1234"))
            myStore.addUser(RegisteredUser(myStore.catalogProduct.size, "josearm21", "josearmando@outlook.es", "12345"))
            myStore.addUser(RegisteredUser(myStore.catalogProduct.size, "maribel07", "maribel@live.com", "123456"))
        }

        private fun setShoppingCart() {
            // User: tomas11
            myStore.listOfUsers[0].addToCart(myStore.catalogProduct[0])
            myStore.listOfUsers[0].addToCart(myStore.catalogProduct[1])
            myStore.listOfUsers[0].addToCart(myStore.catalogProduct[2])
            myStore.listOfUsers[0].addToCart(myStore.catalogProduct[3])

            // User: didier32
            myStore.listOfUsers[1].addToCart(myStore.catalogProduct[4])
            myStore.listOfUsers[1].addToCart(myStore.catalogProduct[5])


            // User: josearm21
            myStore.listOfUsers[2].addToCart(myStore.catalogProduct[6])
            myStore.listOfUsers[2].addToCart(myStore.catalogProduct[7])
            myStore.listOfUsers[2].addToCart(myStore.catalogProduct[8])

            // User: maribel07
            myStore.listOfUsers[3].addToCart(myStore.catalogProduct[9])
        }

        private fun setFavorites() {
            // User: tomas11
            myStore.listOfUsers[0].addToFavorite(myStore.catalogProduct[10])

            // User: didier32
            myStore.listOfUsers[1].addToFavorite(myStore.catalogProduct[0])
            myStore.listOfUsers[1].addToFavorite(myStore.catalogProduct[8])

            // User: josearm21
            myStore.listOfUsers[2].addToFavorite(myStore.catalogProduct[9])
            myStore.listOfUsers[2].addToFavorite(myStore.catalogProduct[10])
            myStore.listOfUsers[2].addToFavorite(myStore.catalogProduct[11])

            // User: maribel07
            myStore.listOfUsers[3].addToFavorite(myStore.catalogProduct[12])
            myStore.listOfUsers[3].addToFavorite(myStore.catalogProduct[13])
            myStore.listOfUsers[3].addToFavorite(myStore.catalogProduct[14])
            myStore.listOfUsers[3].addToFavorite(myStore.catalogProduct[15])
        }

        private fun setAddress() {
            // User: tomas11
            myStore.listOfUsers[0].setAddress("Francisco Zarco 592, Mexico, Durango, Lerdo, 35150")

            // User: didier32
            myStore.listOfUsers[1].setAddress("Miguel Aleman 119, Mexico, Jalisco, Guadalajara, 21170")

        }

        private fun setPaymentMethod() {
            // User: tomas11
            myStore.listOfUsers[0].setPaymentMethod("Credit card", mapOf("Number" to "1234567890123456", "Date" to "04/22", "Security Number" to "123"))
            myStore.listOfUsers[0].setPaymentMethod("Debit card", mapOf("Number" to "1234567890123457", "Date" to "06/24", "Security Number" to "456"))

            // User: josearm21
            myStore.listOfUsers[2].setPaymentMethod("Credit card", mapOf("Number" to "1234567890123458", "Date" to "10/23", "Security Number" to "789"))
        }
    }
}