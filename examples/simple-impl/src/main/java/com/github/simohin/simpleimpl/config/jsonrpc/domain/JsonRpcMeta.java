package com.github.simohin.simpleimpl.config.jsonrpc.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Опциональный объект с дополнительной информацией об ошибках
 * 
 * @author Захаров Андрей
 * @created 22.10.2019
 */
@Data
@AllArgsConstructor
public class JsonRpcMeta implements Serializable {

    private static final long serialVersionUID = 7894633537278142230L;

    private ValidationError validationError;

    @Data
    @AllArgsConstructor
    public static class ValidationError {

        /**
         * Короткое сообщение
         */
        @JsonProperty("title")
        private final String title;

        /**
         * Развернутый формат ошибки
         */
        @JsonProperty("description")
        private final String description;
    }

}
