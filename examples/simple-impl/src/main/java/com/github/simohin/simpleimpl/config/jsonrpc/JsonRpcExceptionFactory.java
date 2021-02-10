package com.github.simohin.simpleimpl.config.jsonrpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.simohin.simpleimpl.config.jsonrpc.domain.JsonRpcError;
import com.github.simohin.simpleimpl.config.jsonrpc.domain.JsonRpcErrorData;
import com.github.simohin.simpleimpl.config.jsonrpc.domain.JsonRpcMeta;
import com.github.simohin.simpleimpl.config.jsonrpc.exception.JsonRpcApiAccessDeniedException;
import com.github.simohin.simpleimpl.config.jsonrpc.exception.JsonRpcApiRuntimeException;
import com.github.simohin.simpleimpl.config.jsonrpc.exception.JsonRpcApiTemporarilyUnavailableException;
import com.github.simohin.simpleimpl.config.jsonrpc.exception.JsonRpcApiValidationException;
import com.github.simohin.simpleimpl.config.jsonrpc.exception.JsonrpcValidationException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Фабрика для генерации исключений разных
 *
 * @author Rozhkov Maksim
 * @created 12.12.16
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonRpcExceptionFactory {

    public static JsonRpcApiValidationException
            domainValidationException(JsonrpcValidationException validationException) {

        return new JsonRpcApiValidationException(
                new JsonRpcErrorData(getJsonRpcErrors(validationException.getValidationCode())));
    }

    public static JsonRpcApiRuntimeException createApiRuntimeException(List<String> codes) {

        if (codes.isEmpty()) {
            return new JsonRpcApiRuntimeException(
                    new JsonRpcErrorData(getJsonRpcErrors("something_went_wrong_critical")));
        }

        return new JsonRpcApiRuntimeException(new JsonRpcErrorData(getJsonRpcErrors(codes)));
    }

    public static JsonRpcApiRuntimeException createApiRuntimeException(String code) {

        return new JsonRpcApiRuntimeException(new JsonRpcErrorData(getJsonRpcErrors(code)));
    }

    public static JsonRpcApiAccessDeniedException createApiAccessDeniedException() {

        return new JsonRpcApiAccessDeniedException(
                new JsonRpcErrorData(getJsonRpcErrors("access_denied")));
    }

    public static JsonRpcApiTemporarilyUnavailableException createApiTempUnavailableException() {

        return new JsonRpcApiTemporarilyUnavailableException(
                new JsonRpcErrorData(getJsonRpcErrors("something_went_wrong_critical")));
    }

    public static JsonRpcApiTemporarilyUnavailableException
            createApiTempUnavailableException(String code) {

        return new JsonRpcApiTemporarilyUnavailableException(
                new JsonRpcErrorData(getJsonRpcErrors(code)));
    }

    private static List<JsonRpcError> getJsonRpcErrors(String error) {

        return getJsonRpcErrors(Collections.singletonList(error));
    }

    private static List<JsonRpcError> getJsonRpcErrors(List<String> errors) {

        List<JsonRpcError> rpcErrors = new ArrayList<>();

        for (String error : errors) {
            var message = GroovyMessageSourceAccessor.getMessage(error);
            var jsonRpcError = new JsonRpcError(error, null, message.getMessage());
            jsonRpcError.setMeta(new JsonRpcMeta(
                    new JsonRpcMeta.ValidationError(message.getTitle(), message.getMessage())));
            rpcErrors.add(jsonRpcError);
        }
        return rpcErrors;
    }
}
