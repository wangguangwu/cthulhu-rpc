package com.cthulhu.common.constants;

/**
 * RPC常量定义
 */
public class RpcConstants {
    
    /**
     * 默认服务版本号
     */
    public static final String DEFAULT_VERSION = "1.0.0";
    
    /**
     * 默认服务分组
     */
    public static final String DEFAULT_GROUP = "default";
    
    /**
     * 默认字符集
     */
    public static final String DEFAULT_CHARSET = "UTF-8";
    
    /**
     * 默认超时时间（毫秒）
     */
    public static final int DEFAULT_TIMEOUT = 5000;
    
    /**
     * 默认重试次数
     */
    public static final int DEFAULT_RETRIES = 2;
    
    /**
     * 魔数，用于标识RPC协议
     */
    public static final byte[] MAGIC_NUMBER = {(byte) 'C', (byte) 'T', (byte) 'H', (byte) 'U'};
    
    /**
     * 协议版本
     */
    public static final byte VERSION = 1;
    
    /**
     * 请求类型
     */
    public static final byte REQUEST_TYPE = 1;
    
    /**
     * 响应类型
     */
    public static final byte RESPONSE_TYPE = 2;
    
    /**
     * 心跳请求类型
     */
    public static final byte HEARTBEAT_REQUEST_TYPE = 3;
    
    /**
     * 心跳响应类型
     */
    public static final byte HEARTBEAT_RESPONSE_TYPE = 4;
}
