package com.github.simohin.simpleimpl.api.jsonrpc.pingpong;

import org.springframework.stereotype.Component;

import com.github.simohin.simpleimpl.service.jsonrpc.PingPongService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 * @author Timofei Simohin
 * @created 09.02.2021
 */
@Component @AutoJsonRpcServiceImpl @RequiredArgsConstructor public class PingPongControllerImpl
    implements PingPongController {

    private final PingPongService service;

    @Override public String ping(String value) {

        return service.pingPong(value);
    }
}
