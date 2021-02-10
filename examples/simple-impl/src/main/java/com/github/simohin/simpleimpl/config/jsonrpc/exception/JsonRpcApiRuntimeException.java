package com.github.simohin.simpleimpl.config.jsonrpc.exception;

import com.github.simohin.simpleimpl.config.jsonrpc.domain.JsonRpcErrorData;

import lombok.Getter;
import lombok.ToString;

/**
 * Ошибка времени исполнения в api. Будем использовать этот exception для ошибок, связанных с
 * невозможностью обработки данных, которые приходят в запросах. При маппинге такого сообщения в
 * ответ JsonRpc сообщение об ошибке попадёт в ответ.
 *
 * Внимание! Не используем этот exception для ошибок бизнес-логики! Для ошибок валидации есть
 * ValidationException. Для ошибок, вызванных иными причинами системного свойства -
 * GeneralRuntimeException.
 */
@ToString
@Getter
public class JsonRpcApiRuntimeException extends JsonRpcApiException {

    private static final long serialVersionUID = -2603397016657465451L;

    /**
     * Код
     */
    private static final Integer CODE = -32603;

    /**
     * Сообщение
     */
    private static final String SHORT_MESSAGE = "Failure";

    /**
     * Блок дата в сообщении
     */
    private final JsonRpcErrorData data;

    /**
     * Создать исключение
     *
     * @param data
     *            - даные
     */
    public JsonRpcApiRuntimeException(JsonRpcErrorData data) {

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
