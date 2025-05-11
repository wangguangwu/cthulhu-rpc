package com.cthulhu.rpc.protocol.serialization;

import com.cthulhu.rpc.core.extension.SPI;

import java.io.IOException;

/**
 * 序列化接口
 * 
 * @author wangguangwu
 */
@SPI
public interface Serialization {
    
    /**
     * 获取序列化类型
     * 
     * @return 序列化类型
     */
    byte getContentTypeId();
    
    /**
     * 获取序列化类型名称
     * 
     * @return 序列化类型名称
     */
    String getContentType();
    
    /**
     * 创建序列化器
     * 
     * @return 序列化器
     */
    ObjectOutput serialize() throws IOException;
    
    /**
     * 创建反序列化器
     * 
     * @return 反序列化器
     */
    ObjectInput deserialize() throws IOException;
}
