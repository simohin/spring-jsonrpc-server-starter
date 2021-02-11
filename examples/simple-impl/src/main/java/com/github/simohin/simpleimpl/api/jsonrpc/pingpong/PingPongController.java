package com.github.simohin.simpleimpl.api.jsonrpc.pingpong;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

/**
 * @author Timofei Simohin
 * @created 08.02.2021
 */
@JsonRpcService("/api/v1/jsonrpc/pingpong")
public interface PingPongController {

    @JsonRpcMethod(value = "ping")
    String ping(@JsonRpcParam("value") String value);

}
