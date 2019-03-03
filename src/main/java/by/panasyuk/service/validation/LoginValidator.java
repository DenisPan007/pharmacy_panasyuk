package by.panasyuk.service.validation;

public class LoginValidator extends FormValidator implements ValidationService {
    public LoginValidator(){
        super.pattern = "^[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])$";
    }
    
}
