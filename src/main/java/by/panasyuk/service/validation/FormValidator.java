package by.panasyuk.service.validation;

public abstract class FormValidator implements ValidationService {
    protected String pattern;
    @Override
    public boolean isValid(String data) {
        return data.matches(pattern);
    }
}
