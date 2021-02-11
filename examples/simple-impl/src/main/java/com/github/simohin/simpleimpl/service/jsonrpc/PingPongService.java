package com.github.simohin.simpleimpl.service.jsonrpc;

import org.springframework.stereotype.Service;

/**
 * @author Timofei Simohin
 * @created 09.02.2021
 */
@Service
public class PingPongService {

    public String pingPong(String value) {

        return "Pong " + value;
    }

}
