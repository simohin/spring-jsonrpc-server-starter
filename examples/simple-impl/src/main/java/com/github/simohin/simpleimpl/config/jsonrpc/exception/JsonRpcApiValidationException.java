package com.github.simohin.simpleimpl.config.jsonrpc.exception;

import com.github.simohin.simpleimpl.config.jsonrpc.domain.JsonRpcErrorData;

import lombok.Getter;
import lombok.ToString;

/**
 * Ошибка валидации поля
 *
 * @author Rozhkov Maksim
 * @created 12.12.16
 */
@ToString
@Getter
public class JsonRpcApiValidationException extends JsonRpcApiException {

    private static final long serialVersionUID = -8700660372625773547L;

    /**
     * Код
     */
    private static final Integer CODE = 6001;

    /**
     * Сообщение
     */
    private static final String SHORT_MESSAGE = "Invalid object";

    /**
     * Блок дата в сообщении
     */
    private final JsonRpcErrorData data;

    /**
     * Выбросить исключение
     *
     * @param data
     *            - даные
     */
    public JsonRpcApiValidationException(JsonRpcErrorData data) {

        this.data = data;
    }

    /**
     * Код ошибки
     *
     * @return ошибка
     */
    @Override
    public Integer getCode() {

        return CODE;
    }

    /**
     * Сообщение
     *
     * @return короткое сообщение
     */
    @Override
    public String getShortMessage() {

        return SHORT_MESSAGE;
    }
}
