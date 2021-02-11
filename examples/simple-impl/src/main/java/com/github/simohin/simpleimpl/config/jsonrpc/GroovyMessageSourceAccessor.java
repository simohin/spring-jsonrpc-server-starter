package com.github.simohin.simpleimpl.config.jsonrpc;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.util.ResourceUtils;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyObjectSupport;
import groovy.lang.GroovyShell;
import groovy.lang.MetaProperty;
import groovy.util.DelegatingScript;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Захаров Андрей
 * @created 11.02.2020
 */
@Slf4j
public enum GroovyMessageSourceAccessor {

    INSTANT;

    private static final String RESOURCES_DIR_NAME = "messages";

    private final ConcurrentHashMap<String, Message> messages =
            new ConcurrentHashMap<>();

    GroovyMessageSourceAccessor() {

        var cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName());
        GroovyShell sh = new GroovyShell(this.getClass().getClassLoader(), new Binding(), cc);
        DelegatingScript script;
        File files;
        try {
            files = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + RESOURCES_DIR_NAME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var filesInDirectory = files.listFiles();

        if (filesInDirectory != null) {

            for (File file : filesInDirectory) {
                try {
                    script = (DelegatingScript) sh.parse(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                var message = new MessageScript();
                script.setDelegate(message);
                script.run();
            }
        }
    }

    public static Message getMessage(String code) {

        return INSTANT.messages.getOrDefault(code, new Message());
    }

    /**
     * Дополнительная структурка, которая контактирует с groovy
     */
    private class MessageScript extends GroovyObjectSupport {

        public void methodMissing(String name, Object args) {

            MetaProperty metaProperty = getMetaClass().getMetaProperty(name);

            Closure closure = (Closure) ((Object[]) args)[0];
            var message = new Message();
            message.generate(closure);
            messages.putIfAbsent(name, message);

        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    protected static class Message extends GroovyObjectSupport {

        private String title = "Упс";

        private String message = "Что-то пошло не так";

        /**
         * Метод для дозаполнения объекта из замыкания groovy
         *
         * @param c
         *            замыкание
         */
        protected void generate(Closure c) {

            c.setDelegate(this);
            c.setResolveStrategy(Closure.DELEGATE_FIRST);
            c.call();
            if (title == null || message == null) {
                throw new RuntimeException(
                    "Empty property in validation message. Please check resources/validation.groovy");
            }
        }
    }
}
