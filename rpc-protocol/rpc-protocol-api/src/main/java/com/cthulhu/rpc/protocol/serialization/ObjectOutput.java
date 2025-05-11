package com.cthulhu.rpc.protocol.serialization;

import java.io.IOException;

/**
 * 对象序列化输出接口
 * 
 * @author wangguangwu
 */
public interface ObjectOutput {
    
    /**
     * 写入布尔值
     * 
     * @param v 布尔值
     * @throws IOException IO异常
     */
    void writeBool(boolean v) throws IOException;
    
    /**
     * 写入字节
     * 
     * @param v 字节
     * @throws IOException IO异常
     */
    void writeByte(byte v) throws IOException;
    
    /**
     * 写入短整型
     * 
     * @param v 短整型
     * @throws IOException IO异常
     */
    void writeShort(short v) throws IOException;
    
    /**
     * 写入整型
     * 
     * @param v 整型
     * @throws IOException IO异常
     */
    void writeInt(int v) throws IOException;
    
    /**
     * 写入长整型
     * 
     * @param v 长整型
     * @throws IOException IO异常
     */
    void writeLong(long v) throws IOException;
    
    /**
     * 写入浮点型
     * 
     * @param v 浮点型
     * @throws IOException IO异常
     */
    void writeFloat(float v) throws IOException;
    
    /**
     * 写入双精度浮点型
     * 
     * @param v 双精度浮点型
     * @throws IOException IO异常
     */
    void writeDouble(double v) throws IOException;
    
    /**
     * 写入字符串
     * 
     * @param v 字符串
     * @throws IOException IO异常
     */
    void writeUTF(String v) throws IOException;
    
    /**
     * 写入字节数组
     * 
     * @param v 字节数组
     * @throws IOException IO异常
     */
    void writeBytes(byte[] v) throws IOException;
    
    /**
     * 写入字节数组
     * 
     * @param v 字节数组
     * @param off 偏移量
     * @param len 长度
     * @throws IOException IO异常
     */
    void writeBytes(byte[] v, int off, int len) throws IOException;
    
    /**
     * 写入对象
     * 
     * @param obj 对象
     * @throws IOException IO异常
     */
    void writeObject(Object obj) throws IOException;
    
    /**
     * 刷新缓冲区
     * 
     * @throws IOException IO异常
     */
    void flushBuffer() throws IOException;
}
