package br.unitins.tp1.pizzaria.validation;

public class ValidationException extends RuntimeException {

    private String fieldName;

    public ValidationException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
