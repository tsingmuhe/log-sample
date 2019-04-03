package org.slf4j.impl;

import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.spi.LocationAwareLogger;

import java.io.PrintStream;

public class SimpleLogger extends MarkerIgnoringBase {
    protected static final int LOG_LEVEL_TRACE = LocationAwareLogger.TRACE_INT;
    protected static final int LOG_LEVEL_DEBUG = LocationAwareLogger.DEBUG_INT;
    protected static final int LOG_LEVEL_INFO = LocationAwareLogger.INFO_INT;
    protected static final int LOG_LEVEL_WARN = LocationAwareLogger.WARN_INT;
    protected static final int LOG_LEVEL_ERROR = LocationAwareLogger.ERROR_INT;


    protected int currentLogLevel = LOG_LEVEL_INFO;

    SimpleLogger(String name) {
        this.name = name;
    }

    private void log(int level, String message, Throwable t) {
        StringBuilder buf = new StringBuilder(32);

        buf.append(System.currentTimeMillis());
        buf.append(' ');

        buf.append('[');
        buf.append(Thread.currentThread().getName());
        buf.append("] ");

        buf.append('[');
        buf.append(renderLevel(level));
        buf.append(']');
        buf.append(' ');

        buf.append(name).append(" - ");

        // Append the message
        buf.append(message);

        write(buf, t);
    }

    protected String renderLevel(int level) {
        switch (level) {
            case LOG_LEVEL_TRACE:
                return "TRACE";
            case LOG_LEVEL_DEBUG:
                return ("DEBUG");
            case LOG_LEVEL_INFO:
                return "INFO";
            case LOG_LEVEL_WARN:
                return "WARN";
            case LOG_LEVEL_ERROR:
                return "ERROR";
        }

        throw new IllegalStateException("Unrecognized level [" + level + "]");
    }

    void write(StringBuilder buf, Throwable t) {
        PrintStream targetStream = System.out;

        targetStream.println(buf.toString());
        writeThrowable(t, targetStream);
        targetStream.flush();
    }

    protected void writeThrowable(Throwable t, PrintStream targetStream) {
        if (t != null) {
            t.printStackTrace(targetStream);
        }
    }

    protected boolean isLevelEnabled(int logLevel) {
        // log level are numerically ordered so can use simple numeric
        // comparison
        return (logLevel >= currentLogLevel);
    }

    @Override
    public boolean isTraceEnabled() {
        return isLevelEnabled(LOG_LEVEL_TRACE);
    }

    @Override
    public void trace(String msg) {
        log(LOG_LEVEL_TRACE, msg, null);
    }

    @Override
    public void trace(String format, Object arg) {
        log(LOG_LEVEL_TRACE, String.format(format, arg), null);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        log(LOG_LEVEL_TRACE, String.format(format, arg1, arg2), null);
    }

    @Override
    public void trace(String format, Object... arguments) {
        log(LOG_LEVEL_TRACE, String.format(format, arguments), null);
    }

    @Override
    public void trace(String msg, Throwable t) {
        log(LOG_LEVEL_TRACE, msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return isLevelEnabled(LOG_LEVEL_DEBUG);
    }

    @Override
    public void debug(String msg) {
        log(LOG_LEVEL_DEBUG, msg, null);
    }

    @Override
    public void debug(String format, Object arg) {
        log(LOG_LEVEL_DEBUG, String.format(format, arg), null);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        log(LOG_LEVEL_DEBUG, String.format(format, arg1, arg2), null);
    }

    @Override
    public void debug(String format, Object... arguments) {
        log(LOG_LEVEL_DEBUG, String.format(format, arguments), null);
    }

    @Override
    public void debug(String msg, Throwable t) {
        log(LOG_LEVEL_DEBUG, msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return isLevelEnabled(LOG_LEVEL_INFO);
    }

    @Override
    public void info(String msg) {
        log(LOG_LEVEL_INFO, msg, null);
    }

    @Override
    public void info(String format, Object arg) {
        log(LOG_LEVEL_INFO, String.format(format, arg), null);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        log(LOG_LEVEL_INFO, String.format(format, arg1, arg2), null);
    }

    @Override
    public void info(String format, Object... arguments) {
        log(LOG_LEVEL_INFO, String.format(format, arguments), null);
    }

    @Override
    public void info(String msg, Throwable t) {
        log(LOG_LEVEL_INFO, msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return isLevelEnabled(LOG_LEVEL_WARN);
    }

    @Override
    public void warn(String msg) {
        log(LOG_LEVEL_WARN, msg, null);
    }

    @Override
    public void warn(String format, Object arg) {
        log(LOG_LEVEL_WARN, String.format(format, arg), null);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        log(LOG_LEVEL_WARN, String.format(format, arg1, arg2), null);
    }

    @Override
    public void warn(String format, Object... arguments) {
        log(LOG_LEVEL_WARN, String.format(format, arguments), null);
    }

    @Override
    public void warn(String msg, Throwable t) {
        log(LOG_LEVEL_WARN, msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return isLevelEnabled(LOG_LEVEL_ERROR);
    }

    @Override
    public void error(String msg) {
        log(LOG_LEVEL_ERROR, msg, null);
    }

    @Override
    public void error(String format, Object arg) {
        log(LOG_LEVEL_ERROR, String.format(format, arg), null);

    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        log(LOG_LEVEL_ERROR, String.format(format, arg1, arg2), null);

    }

    @Override
    public void error(String format, Object... arguments) {
        log(LOG_LEVEL_ERROR, String.format(format, arguments), null);

    }

    @Override
    public void error(String msg, Throwable t) {
        log(LOG_LEVEL_ERROR, msg, t);
    }
}
