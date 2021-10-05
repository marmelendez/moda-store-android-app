package org.bedu.modastoreapp.modelos

import com.google.common.truth.Truth

import org.junit.Test

class RegisteredUserTest {

    @Test
    fun getTotal_Zero() {
        // Given
        val regUser = RegisteredUser(0,"Jose", "jose@hotmail.com", "joseCano123")

        // When
        val res = regUser.getTotal()

        // Then
        Truth.assertThat(res).isEqualTo(0)
    }

    @Test
    fun getTotal_One() {
        // Given
        val regUser = RegisteredUser(0,"Jose", "jose@hotmail.com", "joseCano123")
        val product = Product(0, "camisa", null, "azul", 270f, 123, null)
        val list = mutableListOf(product)
        regUser.setShoppingCart(list)

        // When
        val res = regUser.getTotal()

        // Then
        Truth.assertThat(res).isEqualTo(270)
    }

    @Test
    fun getTotal_350() {
        // Given
        val regUser = RegisteredUser(0,"Jose", "jose@hotmail.com", "joseCano123")
        val product1 = Product(0, "camisa", null, "azul", 270f, 123, null)
        val product2 = Product(1, "pantalon", null, "negro", 480.5f, 123, null)
        val product3 = Product(2, "sudadera", null, "gris", 350f, 125, null)
        val list = mutableListOf(product1, product2, product3)
        regUser.setShoppingCart(list)
        // When
        val res = regUser.getTotal()

        // Then
        Truth.assertThat(res).isEqualTo(1100.5f)
    }
}