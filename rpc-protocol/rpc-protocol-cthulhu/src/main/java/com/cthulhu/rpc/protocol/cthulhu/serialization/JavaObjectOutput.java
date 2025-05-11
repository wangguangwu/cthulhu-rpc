package com.cthulhu.rpc.protocol.cthulhu.serialization;

import com.cthulhu.rpc.protocol.serialization.ObjectOutput;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Java 原生序列化输出实现
 * 
 * @author wangguangwu
 */
public class JavaObjectOutput implements ObjectOutput {
    
    private final ByteArrayOutputStream byteArrayOutputStream;
    private final ObjectOutputStream objectOutputStream;
    
    public JavaObjectOutput() throws IOException {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
    }
    
    @Override
    public void writeBool(boolean v) throws IOException {
        objectOutputStream.writeBoolean(v);
    }
    
    @Override
    public void writeByte(byte v) throws IOException {
        objectOutputStream.writeByte(v);
    }
    
    @Override
    public void writeShort(short v) throws IOException {
        objectOutputStream.writeShort(v);
    }
    
    @Override
    public void writeInt(int v) throws IOException {
        objectOutputStream.writeInt(v);
    }
    
    @Override
    public void writeLong(long v) throws IOException {
        objectOutputStream.writeLong(v);
    }
    
    @Override
    public void writeFloat(float v) throws IOException {
        objectOutputStream.writeFloat(v);
    }
    
    @Override
    public void writeDouble(double v) throws IOException {
        objectOutputStream.writeDouble(v);
    }
    
    @Override
    public void writeUTF(String v) throws IOException {
        objectOutputStream.writeUTF(v);
    }
    
    @Override
    public void writeBytes(byte[] v) throws IOException {
        objectOutputStream.writeInt(v.length);
        objectOutputStream.write(v);
    }
    
    @Override
    public void writeBytes(byte[] v, int off, int len) throws IOException {
        objectOutputStream.writeInt(len);
        objectOutputStream.write(v, off, len);
    }
    
    @Override
    public void writeObject(Object obj) throws IOException {
        objectOutputStream.writeObject(obj);
    }
    
    @Override
    public void flushBuffer() throws IOException {
        objectOutputStream.flush();
    }
    
    /**
     * 获取序列化后的字节数组
     * 
     * @return 字节数组
     */
    public byte[] toByteArray() {
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * 关闭输出流
     * 
     * @throws IOException IO异常
     */
    public void close() throws IOException {
        objectOutputStream.close();
    }
}
