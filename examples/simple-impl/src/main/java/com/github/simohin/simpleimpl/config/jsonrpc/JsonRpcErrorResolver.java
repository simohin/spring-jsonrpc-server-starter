package com.github.simohin.simpleimpl.config.jsonrpc;

import java.lang.reflect.Method;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.simohin.simpleimpl.config.jsonrpc.exception.JsonRpcApiException;
import com.googlecode.jsonrpc4j.ErrorResolver;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Захаров Андрей
 * @created 11.02.2020
 */
@Slf4j
public enum JsonRpcErrorResolver implements ErrorResolver {

    /**
     * ссылка на синглтон
     */
    INSTANCE;

    @Override
    public JsonError resolveError(Throwable throwable, Method method, List<JsonNode> list) {

        if (throwable instanceof JsonRpcApiException) {

            JsonRpcApiException exception = (JsonRpcApiException) throwable;
            return new JsonError(exception.getCode(), exception.getShortMessage(),
                    exception.getData());
        } else if (throwable instanceof JsonMappingException
                || throwable instanceof JsonParseException) {
            log.error(throwable.getMessage(), throwable);
            var exception = JsonRpcExceptionFactory
                    .createApiRuntimeException("something_went_wrong_critical");
            return new JsonError(exception.getCode(), exception.getShortMessage(),
                    exception.getData());
        }

        log.warn("Internal error in json-rpc! method=[{}] json=[{}]", method, list);
        return new JsonError(JsonError.INTERNAL_ERROR.code, "Internal error", null);
    }
}
