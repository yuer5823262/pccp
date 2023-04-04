package com.example.pccp.exception;

public class PccpException extends RuntimeException{
    private final Integer code;
    private final String message;

    public PccpException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public PccpException(PccpExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(),exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
