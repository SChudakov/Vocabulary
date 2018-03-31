package com.sschudakov.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Semen Chudakov on 14.12.2017.
 */
public class LoggersManager {

    private static final String PARSING_LOGGER_NAME = "com.sschudakov.parsing_logger";
    private static final String SQL_LOGGER_NAME = "com.sschudakov.sql_logger";

    private static Logger parsingLogger;
    private static Logger sqlLogger;

    static {
        parsingLogger = LogManager.getLogger(PARSING_LOGGER_NAME);
        sqlLogger = LogManager.getLogger(SQL_LOGGER_NAME);
    }

    public static Logger getParsingLogger() {
        return parsingLogger;
    }

    public static Logger getSqlLogger() {
        return sqlLogger;
    }
}
