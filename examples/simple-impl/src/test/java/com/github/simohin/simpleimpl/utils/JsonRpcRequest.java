package com.github.simohin.simpleimpl.utils;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект для тестирования - обертка для jsonRpc
 *
 * @author Rozhkov Maksim
 * @created 13.12.16
 */
@Data
@NoArgsConstructor
public class JsonRpcRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3615166427173436704L;

    /**
     * Версия
     */
    private String jsonrpc = "2.0";

    /**
     * Ид запроса
     */
    private String id = "1";

    /**
     * имя метода
     */
    private String method;

    /**
     * Параметры
     */
    private Object[] params;
}
