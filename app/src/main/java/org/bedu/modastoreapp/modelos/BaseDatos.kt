/**
 * The default database of the programa
 *
 * Set some default configurations for Category, Product, User, Shopping Cart, Favorites, Address and Payment Method
 */
package org.bedu.modastoreapp.modelos

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
            myStore.addProduct(Product(idProduct++,"Blusa estampada",category[0],"Rosa",130F,mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Blusa de tirantes",category[0],"Azul",300F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Crop top con letras",category[0],"Negro",350F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Vestido largo", category[0],"Rojo",900F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Vestido de fiesta",category[0],"Azul",400F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Short de mezclilla",category[0],"Azul claro",150F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Falda de flores",category[0],"Amarilla",200F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Pantalon de mezclilla",category[0],"Azul",350F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Pantalon formal",category[0],"Negro",400F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Top con cuello",category[0],"Blanco",150F, mapOf("S" to 100, "M" to 50, "X" to 100)))

            // Caballero
            myStore.addProduct(Product(idProduct++,"Playera con cuello",category[1],"Naranja",250F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Pantalon de mezclilla",category[1],"Azul",400F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Camisa con letras",category[1],"Negro",80F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Pants",category[1],"Cafe",120F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Bermuda",category[1],"Verde",250F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Set pijama",category[1],"Azul marino",250F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Camisa marvel",category[1],"Rojo",150F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Pantalon pana",category[1],"Cafe",140F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Pantalon traje",category[1],"Negro",320F, mapOf("S" to 100, "M" to 50, "X" to 100)))
            myStore.addProduct(Product(idProduct++,"Playera",category[1],"Gris",180F, mapOf("S" to 100, "M" to 50, "X" to 100)))
        }


        private fun setUsers() {
            myStore.addUser(RegisteredUser(idUser++.toString(), "tomas11", "tomas@hotmail.com", "123"))
            myStore.addUser(RegisteredUser(idUser++.toString(), "didier32", "didier@hotmail.com", "1234"))
            myStore.addUser(RegisteredUser(idUser++.toString(), "josearm21", "josearmando@outlook.es", "12345"))
            myStore.addUser(RegisteredUser(idUser++.toString(), "maribel07", "maribel@live.com", "123456"))
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