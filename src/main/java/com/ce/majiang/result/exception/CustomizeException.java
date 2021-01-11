package com.ce.majiang.result.exception;

/**
 * @author c__e
 * @date 2021/1/5 21:25
 */
public class CustomizeException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizeException(String message) {
        super(message);
        this.message = message;
    }

    public CustomizeException(ICutomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
