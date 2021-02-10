package com.github.simohin.simpleimpl.config.jsonrpc;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.HttpStatusCodeProvider;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;

/**
 * @author Timofei Simohin
 * @created 08.02.2021
 */
@Configuration
public class JsonRpcConfig {

    @Bean
    public static AutoJsonRpcServiceImplExporter
            autoJsonRpcServiceImplExporter(
                    @Qualifier("jsonObjectMapper") ObjectMapper objectMapper) {

        AutoJsonRpcServiceImplExporter exporter = new AutoJsonRpcServiceImplExporter();

        exporter.setObjectMapper(objectMapper);
        exporter.setErrorResolver(JsonRpcErrorResolver.INSTANCE);
        exporter.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // отключение логгирования исключений в JsonRpcBasicServer
        exporter.setShouldLogInvocationErrors(false);

        HttpStatusCodeProvider statusProvider = new HttpStatusCodeProvider() {

            @Override
            public int getHttpStatusCode(int i) {

                // Всегда отдаём 200 ОК, даже в случае любых ошибок.
                return HttpServletResponse.SC_OK;
            }

            @Override
            public Integer getJsonRpcCode(int i) {

                return 0;
            }
        };
        exporter.setHttpStatusCodeProvider(statusProvider);

        return exporter;
    }

}
