package com.bank.accounts.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resoruceName, String fieldName, String fieldValue){
        super(String.format("%s not found with the given input data %s: %s",resoruceName, fieldName, fieldValue));
    }
}
