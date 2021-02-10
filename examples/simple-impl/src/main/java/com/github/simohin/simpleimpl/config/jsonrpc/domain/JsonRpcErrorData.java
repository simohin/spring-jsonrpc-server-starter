package com.github.simohin.simpleimpl.config.jsonrpc.domain;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Список в блоке data при ответе с ошибками
 *
 * @author Rozhkov Maksim
 * @created 12.12.16
 */
@Data
@AllArgsConstructor
public class JsonRpcErrorData implements Serializable {

    private static final long serialVersionUID = -6709186464735895041L;

    /**
     * Список ошибок в нормальном виде
     */
    private final List<JsonRpcError> errors;
}
