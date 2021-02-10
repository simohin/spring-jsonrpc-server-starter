package com.github.simohin.simpleimpl.config.jsonrpc;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author Andrew Zakharov
 * @created 06.11.2020
 */
@Primary
@Component("jsonObjectMapper")
public class JsonObjectMapper extends ObjectMapper {

    public static final String JSON_OBJECT_MAPPER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public JsonObjectMapper() {

        super();
        registerModule(new JavaTimeModule());

        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        setDateFormat(new SimpleDateFormat(JSON_OBJECT_MAPPER_DATE_FORMAT));
        disable(FAIL_ON_UNKNOWN_PROPERTIES);
        disable(FAIL_ON_EMPTY_BEANS);
        setTimeZone(TimeZone.getDefault());

    }
}
