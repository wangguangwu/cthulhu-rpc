package com.cthulhu.rpc.protocol.cthulhu.message;

import com.cthulhu.rpc.protocol.message.Response;

/**
 * Cthulhu 协议响应消息
 * 
 * @author wangguangwu
 */
public class CthulhuResponse extends CthulhuMessage implements Response {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 响应状态：成功
     */
    public static final byte OK = 0;
    
    /**
     * 响应状态：客户端异常
     */
    public static final byte CLIENT_ERROR = 1;
    
    /**
     * 响应状态：服务端异常
     */
    public static final byte SERVER_ERROR = 2;
    
    /**
     * 响应状态
     */
    private byte status = OK;
    
    /**
     * 响应结果
     */
    private Object result;
    
    /**
     * 异常信息
     */
    private Throwable exception;
    
    public CthulhuResponse() {
        setMessageType((byte) 3); // 响应消息类型
    }
    
    @Override
    public byte getStatus() {
        return status;
    }
    
    /**
     * 设置响应状态
     * 
     * @param status 响应状态
     */
    public void setStatus(byte status) {
        this.status = status;
    }
    
    @Override
    public Object getResult() {
        return result;
    }
    
    /**
     * 设置响应结果
     * 
     * @param result 响应结果
     */
    public void setResult(Object result) {
        this.result = result;
    }
    
    @Override
    public Throwable getException() {
        return exception;
    }
    
    /**
     * 设置异常信息
     * 
     * @param exception 异常信息
     */
    public void setException(Throwable exception) {
        this.exception = exception;
        this.status = exception instanceof RuntimeException ? CLIENT_ERROR : SERVER_ERROR;
    }
}
