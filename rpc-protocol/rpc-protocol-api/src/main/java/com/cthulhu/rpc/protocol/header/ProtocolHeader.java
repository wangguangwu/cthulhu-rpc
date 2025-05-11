package com.cthulhu.rpc.protocol.header;

import java.io.Serializable;

/**
 * 协议头部接口 - 定义协议消息的头部结构
 *
 * @author wangguangwu
 */
public interface ProtocolHeader extends Serializable {
    /**
     * 魔数 - 用于快速识别协议
     */
    short MAGIC = (short) 0xC7C7;
    
    /**
     * 获取魔数
     *
     * @return 魔数
     */
    short getMagic();
    
    /**
     * 获取版本号
     *
     * @return 版本号
     */
    byte getVersion();
    
    /**
     * 获取消息类型
     *
     * @return 消息类型
     */
    byte getMessageType();
    
    /**
     * 获取序列化类型
     *
     * @return 序列化类型
     */
    byte getSerializationType();
    
    /**
     * 获取消息ID
     *
     * @return 消息ID
     */
    long getMessageId();
    
    /**
     * 获取消息体长度
     *
     * @return 消息体长度
     */
    int getBodyLength();
    
    /**
     * 设置消息体长度
     *
     * @param length 消息体长度
     */
    void setBodyLength(int length);
}
