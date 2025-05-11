package com.cthulhu.rpc.core.exception;

/**
 * RPC异常基类
 * 
 * @author cthulhu-rpc
 */
public class RpcException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 未知异常
     */
    public static final int UNKNOWN_EXCEPTION = 0;
    
    /**
     * 网络异常
     */
    public static final int NETWORK_EXCEPTION = 1;
    
    /**
     * 超时异常
     */
    public static final int TIMEOUT_EXCEPTION = 2;
    
    /**
     * 业务异常
     */
    public static final int BIZ_EXCEPTION = 3;
    
    /**
     * 序列化异常
     */
    public static final int SERIALIZATION_EXCEPTION = 4;
    
    /**
     * 异常类型
     */
    private int code;
    
    public RpcException() {
        super();
    }
    
    public RpcException(int code) {
        super();
        this.code = code;
    }
    
    public RpcException(String message) {
        super(message);
    }
    
    public RpcException(int code, String message) {
        super(message);
        this.code = code;
    }
    
    public RpcException(Throwable cause) {
        super(cause);
    }
    
    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public RpcException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
    
    /**
     * 获取异常类型
     * 
     * @return 异常类型
     */
    public int getCode() {
        return code;
    }
    
    /**
     * 设置异常类型
     * 
     * @param code 异常类型
     */
    public void setCode(int code) {
        this.code = code;
    }
}
