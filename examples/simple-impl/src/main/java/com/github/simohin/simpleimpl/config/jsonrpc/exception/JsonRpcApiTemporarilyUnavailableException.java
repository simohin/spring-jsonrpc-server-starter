package com.github.simohin.simpleimpl.config.jsonrpc.exception;

import com.github.simohin.simpleimpl.config.jsonrpc.domain.JsonRpcErrorData;

import lombok.Getter;
import lombok.ToString;

/**
 * Запрос не может быть выполнен ввиду временного отказа в микросервисе или взаимодействующих
 * сервисах. Информируем, что запрос имеет смысл повторить позже.
 *
 * Кидать такое исключение стоит в случаях, когда исходная проблема является признаком отказа,
 * после которого микросервис может восстановиться самостоятельно (например, временная
 * недоступность API Bank).
 */
@ToString
@Getter
public class JsonRpcApiTemporarilyUnavailableException extends JsonRpcApiException {

    private static final long serialVersionUID = -4293254052863871109L;

    /**
     * Код
     */
    private static final Integer CODE = -32001;

    /**
     * Сообщение
     */
    private static final String SHORT_MESSAGE = "Temporarily unavailable";

    private final JsonRpcErrorData data;

    public JsonRpcApiTemporarilyUnavailableException(JsonRpcErrorData data) {

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
