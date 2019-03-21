package by.panasyuk.service.validation;

public class PasswordValidator extends FormValidator implements ValidationService {
    public PasswordValidator(){
        super.pattern = ".{8,40}";
    }
}
