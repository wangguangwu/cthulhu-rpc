package com.cthulhu.rpc.core.exception;

/**
 * 超时异常
 * 
 * @author cthulhu-rpc
 */
public class TimeoutException extends RpcException {
    
    private static final long serialVersionUID = 1L;
    
    public TimeoutException() {
        super(TIMEOUT_EXCEPTION);
    }
    
    public TimeoutException(String message) {
        super(TIMEOUT_EXCEPTION, message);
    }
    
    public TimeoutException(Throwable cause) {
        super(TIMEOUT_EXCEPTION, cause.getMessage(), cause);
    }
    
    public TimeoutException(String message, Throwable cause) {
        super(TIMEOUT_EXCEPTION, message, cause);
    }
}
