package by.panasyuk.service.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordValidatorTest {
    @Test
    public void isValid(){
        ValidationService passwordValidator = new PasswordValidator();
        String password="123456789";
        String password1="1234567";
        assertTrue(passwordValidator.isValid(password));
        assertFalse(passwordValidator.isValid(password1));

    }

}