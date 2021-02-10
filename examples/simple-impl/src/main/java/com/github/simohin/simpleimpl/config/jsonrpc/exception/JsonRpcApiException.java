package com.github.simohin.simpleimpl.config.jsonrpc.exception;

import com.github.simohin.simpleimpl.config.jsonrpc.domain.JsonRpcErrorData;

/**
 * Интерфейс для всех ошибок которые файрятся jsonRpc <br>
 * Что при этом попадает в ответ - протестировано и документировано в JsonRpcErrorCodesTest
 *
 * @author Rozhkov Maksim
 * @created 12.12.16
 */
public abstract class JsonRpcApiException extends RuntimeException {

    public JsonRpcApiException() {

    }

    protected JsonRpcApiException(String message) {

        super(message);
    }

    /**
     * Код ошибки
     * 
     * @return ошибка
     */
    public abstract Integer getCode();

    /**
     * Сообщение
     * 
     * @return короткое сообщение
     */
    public abstract String getShortMessage();

    /**
     * Данные расширенные
     * 
     * @return блок data в jsonError
     */
    public abstract JsonRpcErrorData getData();
}
