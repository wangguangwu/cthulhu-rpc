package com.cthulhu.rpc.core.exception;

/**
 * 序列化异常
 * 
 * @author cthulhu-rpc
 */
public class SerializationException extends RpcException {
    
    private static final long serialVersionUID = 1L;
    
    public SerializationException() {
        super(SERIALIZATION_EXCEPTION);
    }
    
    public SerializationException(String message) {
        super(SERIALIZATION_EXCEPTION, message);
    }
    
    public SerializationException(Throwable cause) {
        super(SERIALIZATION_EXCEPTION, cause.getMessage(), cause);
    }
    
    public SerializationException(String message, Throwable cause) {
        super(SERIALIZATION_EXCEPTION, message, cause);
    }
}
