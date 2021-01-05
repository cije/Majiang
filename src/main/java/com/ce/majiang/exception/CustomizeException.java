package com.ce.majiang.exception;

/**
 * @author c__e
 * @date 2021/1/5 21:25
 */
public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(String message) {
        super(message);
        this.message = message;
    }

    public CustomizeException(ICutomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
