package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SimpleLoggerFactory implements ILoggerFactory {
    ConcurrentMap<String, Logger> loggerMap;

    public SimpleLoggerFactory() {
        loggerMap = new ConcurrentHashMap<>();
    }

    @Override
    public Logger getLogger(String name) {
        Logger simpleLogger = loggerMap.get(name);
        if (simpleLogger != null) {
            return simpleLogger;
        } else {
            Logger newInstance = new SimpleLogger(name);
            Logger oldInstance = loggerMap.putIfAbsent(name, newInstance);
            return oldInstance == null ? newInstance : oldInstance;
        }
    }

    void reset() {
        loggerMap.clear();
    }
}
