package org.bedu.modastoreapp.modelos;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FormTest {
    @Test
    public void getValidateUsername_True() {
        // Given
        String username = "maribel123";

        // When
        Boolean res = Form.validateUsername(username);

        // Then
        assertThat(res).isEqualTo(true);
    }

    @Test
    public void getValidateUsername_False() {
        // Given
        String username = "123";

        // When
        Boolean res = Form.validateUsername(username);

        // Then
        assertEquals(false, res);
    }

    @Test
    public void getValidateEmail_True() {
        // Given
        String email = "tomas@hotmail.com";

        // When
        Boolean res = Form.validateEmail(email);

        // Then
        assertThat(res).isEqualTo(true);
    }

    @Test
    public void getValidateEmail_False() {
        // Given
        String email = "@.com";

        // When
        Boolean res = Form.validateEmail(email);

        // Then
        assertEquals(false, res);
    }

    @Test
    public void getValidatePassword_True() {
        // Given
        String password = "joseCano123";

        // When
        Boolean res = Form.validatePassword(password);

        // Then
        assertThat(res).isEqualTo(true);
    }

    @Test
    public void getValidatePassword_False() {
        // Given
        String password = "jose";

        // When
        Boolean res = Form.validatePassword(password);

        // Then
        assertEquals(false, res);
    }
}