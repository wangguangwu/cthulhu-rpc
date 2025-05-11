package com.cthulhu.rpc.core.extension;

/**
 * 扩展点加载异常
 * 
 * @author cthulhu-rpc
 */
public class ExtensionException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public ExtensionException(String message) {
        super(message);
    }
    
    public ExtensionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ExtensionException(Throwable cause) {
        super(cause);
    }
}
