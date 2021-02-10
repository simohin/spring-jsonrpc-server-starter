package com.github.simohin.simpleimpl.config.jsonrpc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.simohin.simpleimpl.config.jsonrpc.JsonRpcExceptionFactory;
import com.github.simohin.simpleimpl.config.jsonrpc.exception.JsonRpcApiAccessDeniedException;
import com.github.simohin.simpleimpl.config.jsonrpc.exception.JsonRpcApiException;
import com.github.simohin.simpleimpl.config.jsonrpc.exception.JsonRpcApiTemporarilyUnavailableException;
import com.github.simohin.simpleimpl.config.jsonrpc.exception.JsonrpcValidationException;

import lombok.extern.slf4j.Slf4j;

/**
 * Advice для ошибок с наших контроллеров
 *
 * @author Duborenko Maxim
 * @created 09 Июнь 2017 г.
 */
@Aspect
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component("jsonrpcExceptionAdvice")
@Slf4j
public class JsonRpcExceptionResolverAdvice {

    /**
     * Обарачиваем вызов метода контроллера
     *
     * @param jp
     *            Точка сочленения
     *
     * @return результат вызова
     *
     * @throws Throwable
     *             перебрасываем
     */
    @Around("@within(com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl))")
    @Order
    public Object around(ProceedingJoinPoint jp) throws Throwable {

        try {
            return jp.proceed();

        } catch (JsonrpcValidationException vException) {

            // Обработка исключений валидации (возбуждаются в слое бизнес-логики)
            if (!vException.getValidationCode().isBlank()) {
                log.info("Найдены ошибки валидации {}", vException.getValidationCode());
            } else {
                log.warn("Unexpected validation exception", vException);
            }
            throw JsonRpcExceptionFactory.domainValidationException(vException);

        } catch (JsonRpcApiTemporarilyUnavailableException unavailableEx) {
            // Недоступно - так недоступно. Логируем и кидаем дальше
            log.warn("Api unavailable exception occurred");
            throw unavailableEx;
        } catch (JsonRpcApiAccessDeniedException deniedEx) {
            // Нет доступа. Логируем и кидаем дальше
            log.warn("Access denied during API invocation");
            throw deniedEx;
        } catch (JsonRpcApiException apiEx) {
            // Логируем такие исключения как WARN и бросаем дальше
            log.warn("Unexpected API runtime exception", apiEx);
            throw apiEx;
        } catch (Exception anyOtherException) {
            log.error("Unexpected runtime exception occurred", anyOtherException);
            throw JsonRpcExceptionFactory.createApiRuntimeException("something_went_wrong_critical");
        }
    }
}
