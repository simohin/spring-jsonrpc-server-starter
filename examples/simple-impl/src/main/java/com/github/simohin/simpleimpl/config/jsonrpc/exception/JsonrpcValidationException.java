package com.github.simohin.simpleimpl.config.jsonrpc.exception;

import lombok.Getter;

/**
 * Ошибка валидации поля
 */
@Getter
public abstract class JsonrpcValidationException extends RuntimeException {

    private static final String DEFAULT_CODE = "validation_error";

    private static final String DEFAULT_MESSAGE = "Invalid object";

    /**
     * Ошибки валидации
     */
    private final String validationCode;

    /**
     * {@inheritDoc}
     */
    public JsonrpcValidationException() {

        super(DEFAULT_MESSAGE);
        validationCode = DEFAULT_CODE;
    }

    public JsonrpcValidationException(String messageCode) {

        super(DEFAULT_MESSAGE);
        this.validationCode = messageCode;
    }
}
