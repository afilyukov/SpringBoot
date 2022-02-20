package com.geekbrains.spring.boot.exceptions;

public class BusinessException extends Exception {

    private String errorCode = "Unknown_Exception";

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode(){
        return this.errorCode;
    }

}
