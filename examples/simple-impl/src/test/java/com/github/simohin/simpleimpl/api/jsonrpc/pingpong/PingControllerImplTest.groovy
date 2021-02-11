package com.github.simohin.simpleimpl.api.jsonrpc.pingpong


import com.github.simohin.simpleimpl.base.ApplicationContextTest
import com.github.simohin.simpleimpl.utils.MvcTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

/**
 * @author Andrew Zakharov
 * @created 06.11.2020
 */
@MvcTest
class PingControllerImplTest extends ApplicationContextTest {

    private static final String URI = "/api/v1/jsonrpc/pingpong"

    @Autowired
    MockMvc mvc

    def "test ping"() {
        given:
        def value = UUID.randomUUID().toString()

        when:
        def res = mvc.perform(post(URI).content(prepareJsonRpcRequest("ping", value)))

        then:
        res.andExpect(jsonPath('$.result').value("Pong " + value))
    }

}
