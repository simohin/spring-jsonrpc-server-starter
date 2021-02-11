package com.github.simohin.simpleimpl.base

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.simohin.simpleimpl.utils.JsonRpcRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

/**
 * @author Andrew Zakharov
 * @created 06.11.2020
 */
@ActiveProfiles("test")
@SpringBootTest
class ApplicationContextTest extends Specification {

    @Qualifier("jsonObjectMapper")
    @Autowired
    ObjectMapper mapper

    /**
     *
     * @param methodName
     *            название метода
     * @param params
     *            параметры
     * @return json rpc запрос
     */
    protected String prepareJsonRpcRequest(String methodName, Object... params) {

        JsonRpcRequest jsonRpcRequest = new JsonRpcRequest(params: params, method: methodName)

        try {
            return mapper.writeValueAsString(jsonRpcRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
