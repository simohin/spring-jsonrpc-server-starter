package com.github.simohin.simpleimpl.config.jsonrpc.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * Конкретная ошибка
 * 
 * @author Rozhkov Maksim
 * @created 12.12.16
 */
@EqualsAndHashCode
@ToString
public class JsonRpcError implements Serializable {

    private static final long serialVersionUID = 1405118975351599913L;

    public JsonRpcError(String code, String objectName, String message) {

        this.code = code;
        this.objectName = objectName;
        this.message = message;
    }

    /**
     * Код
     */
    @Getter
    @JsonProperty("code")
    private final String code;

    /**
     * Имя поля
     */
    @JsonProperty("object_name")
    private final String objectName;

    /**
     * Мессага
     */
    @JsonProperty("message")
    private final String message;

    /**
     * Доп инфа по ошибкам
     */
    @Setter
    @JsonProperty("meta")
    private JsonRpcMeta meta;
}
