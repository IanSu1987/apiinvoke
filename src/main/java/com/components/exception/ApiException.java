package com.components.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ian.Su
 * @version $Id: ApiException.java, v 0.1 2017/7/10 9:25 Ian.Su Exp $
 */
public class ApiException extends Exception {

    private static Logger LOGGER = LoggerFactory.getLogger(ApiException.class);


    public ApiException(String message) {
        super(message);
        LOGGER.error(message);
    }


    public ApiException(String message, Throwable cause) {
        super(message + cause.getMessage(), cause);
        LOGGER.error(message, cause);
    }


}
