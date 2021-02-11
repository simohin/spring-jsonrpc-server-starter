package com.github.simohin.simpleimpl.utils;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Maxim Yurkov
 * @created 12.12.19
 */
@Data
@NoArgsConstructor
public class JsonRpcResponse<T> implements Serializable {

    private static final long serialVersionUID = 1136443094300771989L;

    /**
     * Версия
     */
    private String jsonrpc = "2.0";

    /**
     * Ид запроса
     */
    private String id = "1";

    /**
     * Параметры
     */
    private T result;
}
