package com.cthulhu.rpc.protocol.serialization;

import java.io.IOException;

/**
 * 对象反序列化输入接口
 * 
 * @author wangguangwu
 */
public interface ObjectInput {
    
    /**
     * 读取布尔值
     * 
     * @return 布尔值
     * @throws IOException IO异常
     */
    boolean readBool() throws IOException;
    
    /**
     * 读取字节
     * 
     * @return 字节
     * @throws IOException IO异常
     */
    byte readByte() throws IOException;
    
    /**
     * 读取短整型
     * 
     * @return 短整型
     * @throws IOException IO异常
     */
    short readShort() throws IOException;
    
    /**
     * 读取整型
     * 
     * @return 整型
     * @throws IOException IO异常
     */
    int readInt() throws IOException;
    
    /**
     * 读取长整型
     * 
     * @return 长整型
     * @throws IOException IO异常
     */
    long readLong() throws IOException;
    
    /**
     * 读取浮点型
     * 
     * @return 浮点型
     * @throws IOException IO异常
     */
    float readFloat() throws IOException;
    
    /**
     * 读取双精度浮点型
     * 
     * @return 双精度浮点型
     * @throws IOException IO异常
     */
    double readDouble() throws IOException;
    
    /**
     * 读取字符串
     * 
     * @return 字符串
     * @throws IOException IO异常
     */
    String readUTF() throws IOException;
    
    /**
     * 读取字节数组
     * 
     * @return 字节数组
     * @throws IOException IO异常
     */
    byte[] readBytes() throws IOException;
    
    /**
     * 读取对象
     * 
     * @return 对象
     * @throws IOException IO异常
     * @throws ClassNotFoundException 类未找到异常
     */
    Object readObject() throws IOException, ClassNotFoundException;
    
    /**
     * 读取对象
     * 
     * @param cls 对象类型
     * @param <T> 对象类型
     * @return 对象
     * @throws IOException IO异常
     * @throws ClassNotFoundException 类未找到异常
     */
    <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException;
}
