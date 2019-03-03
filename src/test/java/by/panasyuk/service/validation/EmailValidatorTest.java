package by.panasyuk.service.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {
    @Test
    public void isValid(){
        ValidationService emailValidator = new EmailValidator();
        String validEmail1="denis@mail.ru";
        String validEmail2="denis@gmail.com";
        String invalidEmail1="1234567";
        String invalidEmail2="denis@mail.c";
        String invalidEmail3="@mail.";
        assertTrue(emailValidator.isValid(validEmail1));
        assertTrue(emailValidator.isValid(validEmail2));
        assertFalse(emailValidator.isValid(invalidEmail1));
        assertFalse(emailValidator.isValid(invalidEmail2));
        assertFalse(emailValidator.isValid(invalidEmail3));


    }

}