package com.twb.robot.common.datasource.common.dao;


/**
 * DAOSystemException is thrown by a DAO component when there is
 * some irrecoverable error (like SQLException)
 */
public class DAOSystemException extends RuntimeException {

    public DAOSystemException() {}
    public DAOSystemException(String msg) { super(msg); }
    public DAOSystemException(String msg, Throwable cause) {
        super(msg+"\n  nest exception:"+cause.getMessage(), cause);
    }
    public DAOSystemException(Throwable cause) {
        super(cause);
    }
}
