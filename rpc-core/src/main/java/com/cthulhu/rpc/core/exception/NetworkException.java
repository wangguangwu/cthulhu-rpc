package com.cthulhu.rpc.core.exception;

/**
 * 网络异常
 * 
 * @author cthulhu-rpc
 */
public class NetworkException extends RpcException {
    
    private static final long serialVersionUID = 1L;
    
    public NetworkException() {
        super(NETWORK_EXCEPTION);
    }
    
    public NetworkException(String message) {
        super(NETWORK_EXCEPTION, message);
    }
    
    public NetworkException(Throwable cause) {
        super(NETWORK_EXCEPTION, cause.getMessage(), cause);
    }
    
    public NetworkException(String message, Throwable cause) {
        super(NETWORK_EXCEPTION, message, cause);
    }
}
