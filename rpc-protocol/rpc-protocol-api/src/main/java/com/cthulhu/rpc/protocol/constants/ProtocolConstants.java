package com.cthulhu.rpc.protocol.constants;

/**
 * 协议常量类 - 定义协议相关的常量
 *
 * @author wangguangwu
 */
public class ProtocolConstants {
    /**
     * 序列化类型常量
     */
    public static class SerializationType {
        /**
         * JSON序列化
         */
        public static final byte JSON = 1;
        
        /**
         * Protobuf序列化
         */
        public static final byte PROTOBUF = 2;
        
        /**
         * Hessian序列化
         */
        public static final byte HESSIAN = 3;
        
        /**
         * Java序列化
         */
        public static final byte JAVA = 4;
    }
    
    /**
     * 消息类型常量
     */
    public static class MessageType {
        /**
         * 请求消息
         */
        public static final byte REQUEST = 1;
        
        /**
         * 响应消息
         */
        public static final byte RESPONSE = 2;
        
        /**
         * 心跳请求
         */
        public static final byte HEARTBEAT_REQUEST = 3;
        
        /**
         * 心跳响应
         */
        public static final byte HEARTBEAT_RESPONSE = 4;
    }
    
    /**
     * 协议版本常量
     */
    public static class Version {
        /**
         * 当前版本
         */
        public static final byte CURRENT_VERSION = 1;
    }
}
