package com.cthulhu.rpc.protocol.exception;

import com.cthulhu.rpc.core.exception.RpcException;

/**
 * 协议异常类 - 处理协议层面的错误
 *
 * @author wangguangwu
 */
public class ProtocolException extends RpcException {
    private static final long serialVersionUID = 1L;
    
    /**
     * 协议解析错误
     */
    public static final int PROTOCOL_PARSE_ERROR = 100;
    
    /**
     * 协议版本不支持
     */
    public static final int PROTOCOL_VERSION_NOT_SUPPORTED = 101;
    
    /**
     * 序列化类型不支持
     */
    public static final int SERIALIZATION_TYPE_NOT_SUPPORTED = 102;
    
    /**
     * 消息类型不支持
     */
    public static final int MESSAGE_TYPE_NOT_SUPPORTED = 103;
    
    /**
     * 构造函数
     *
     * @param code 错误码
     */
    public ProtocolException(int code) {
        super(code);
    }
    
    /**
     * 构造函数
     *
     * @param code 错误码
     * @param message 错误信息
     */
    public ProtocolException(int code, String message) {
        super(code, message);
    }
    
    /**
     * 构造函数
     *
     * @param message 错误信息
     * @param cause 异常原因
     */
    public ProtocolException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 构造函数
     *
     * @param code 错误码
     * @param message 错误信息
     * @param cause 异常原因
     */
    public ProtocolException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
