package com.github.simohin.simpleimpl.api.jsonrpc

import com.github.simohin.simpleimpl.base.ApplicationContextTest
import com.github.simohin.simpleimpl.config.jsonrpc.JsonRpcExceptionFactory
import com.github.simohin.simpleimpl.config.jsonrpc.exception.JsonrpcValidationException
import com.github.simohin.simpleimpl.service.jsonrpc.PingPongService
import com.github.simohin.simpleimpl.utils.MvcTest
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

/**
 * @author Timofei Simohin
 * @created 09.02.2021
 */
@MvcTest
class JsonRpcErrorResolverTest extends ApplicationContextTest {

    private static final String URI = "/api/v1/jsonrpc/pingpong"

    @Autowired
    MockMvc mvc

    @SpringBean
    private PingPongService pingPongServiceMock = Mock()

    def "test error resolving"() {

        when:
        def res = mvc.perform(post(URI).content(prepareJsonRpcRequest("ping", UUID.randomUUID().toString()))).andReturn().getResponse().getContentAsString()

        then:
        for (String code : codes) {
            res.contains(code)
        }
        1 * pingPongServiceMock.pingPong(_ as String) >> { throw exception }

        where:
        exception                                                                                          | codes
        JsonRpcExceptionFactory.createApiAccessDeniedException()                                           | ["access_denied"]
        new JsonrpcValidationException() {
        }                                                                                                  | ["validation_error"]
        JsonRpcExceptionFactory.createApiTempUnavailableException()                                        | ["something_went_wrong_critical"]
        JsonRpcExceptionFactory.createApiRuntimeException("jsonrpc_duplicate")                             | ["jsonrpc_duplicate"]
        JsonRpcExceptionFactory.createApiRuntimeException(["jsonrpc_duplicate", "jsonrpc_invalid_format"]) | ["jsonrpc_duplicate", "jsonrpc_invalid_format"]

    }
}
