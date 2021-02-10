package com.github.simohin.simpleimpl.config.jsonrpc.exception;

import com.github.simohin.simpleimpl.config.jsonrpc.domain.JsonRpcErrorData;

import lombok.Getter;
import lombok.ToString;

/**
 * Ошибка доступа (доступ к методу api или к кастомеру запрещен системой безопасности)
 */
@ToString
@Getter
public class JsonRpcApiAccessDeniedException extends JsonRpcApiException {

    private static final long serialVersionUID = 1397974539180910852L;

    /**
     * Код
     */
    private static final Integer CODE = 7001;

    /**
     * Сообщение
     */
    private static final String SHORT_MESSAGE = "Access denied";

    /**
     * Блок дата в сообщении
     */
    private final JsonRpcErrorData data;

    public JsonRpcApiAccessDeniedException(JsonRpcErrorData data) {

        super(SHORT_MESSAGE);
        this.data = data;
    }

    @Override
    public Integer getCode() {
        return CODE;
    }

    @Override
    public String getShortMessage() {
        return SHORT_MESSAGE;
    }
}
