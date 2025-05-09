package ru.alex9043.wrtesttask.exception;

import ru.alex9043.wrtesttask.model.enums.ServicesType;

import java.util.Arrays;


public class InvalidServiceTypeException extends RuntimeException {
    private final String invalidValue;
    private final String validValues;

    public InvalidServiceTypeException(String invalidValue) {
        super("Invalid service type: '" + invalidValue + "'");
        this.invalidValue = invalidValue;
        this.validValues = Arrays.stream(ServicesType.values())
                .map(Enum::name)
                .toList().toString();
    }

    public String getInvalidValue() {
        return invalidValue;
    }

    public String getValidValues() {
        return validValues;
    }
}
