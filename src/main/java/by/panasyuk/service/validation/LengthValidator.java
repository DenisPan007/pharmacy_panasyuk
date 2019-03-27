package by.panasyuk.service.validation;

public class LengthValidator extends FormValidator implements ValidationService {
    public LengthValidator() {
        super.pattern = "^.{0,40}$";
    }
}
