package by.panasyuk.service.validation;

public class EmailValidator extends FormValidator implements ValidationService {
    public EmailValidator() {
        super.pattern = "^(.{0,10})@(.{0,10})\\.(.{2,10})$";
    }
}
