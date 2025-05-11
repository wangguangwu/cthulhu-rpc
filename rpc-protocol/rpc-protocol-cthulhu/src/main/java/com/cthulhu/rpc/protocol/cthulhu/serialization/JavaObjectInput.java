package com.cthulhu.rpc.protocol.cthulhu.serialization;

import com.cthulhu.rpc.protocol.serialization.ObjectInput;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Java 原生反序列化输入实现
 * 
 * @author wangguangwu
 */
public class JavaObjectInput implements ObjectInput {
    
    private final ObjectInputStream objectInputStream;
    
    public JavaObjectInput() throws IOException {
        this(new byte[0]);
    }
    
    public JavaObjectInput(byte[] bytes) throws IOException {
        this.objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
    }
    
    @Override
    public boolean readBool() throws IOException {
        return objectInputStream.readBoolean();
    }
    
    @Override
    public byte readByte() throws IOException {
        return objectInputStream.readByte();
    }
    
    @Override
    public short readShort() throws IOException {
        return objectInputStream.readShort();
    }
    
    @Override
    public int readInt() throws IOException {
        return objectInputStream.readInt();
    }
    
    @Override
    public long readLong() throws IOException {
        return objectInputStream.readLong();
    }
    
    @Override
    public float readFloat() throws IOException {
        return objectInputStream.readFloat();
    }
    
    @Override
    public double readDouble() throws IOException {
        return objectInputStream.readDouble();
    }
    
    @Override
    public String readUTF() throws IOException {
        return objectInputStream.readUTF();
    }
    
    @Override
    public byte[] readBytes() throws IOException {
        int len = objectInputStream.readInt();
        if (len < 0) {
            return null;
        } else if (len == 0) {
            return new byte[0];
        } else {
            byte[] result = new byte[len];
            objectInputStream.readFully(result);
            return result;
        }
    }
    
    @Override
    public Object readObject() throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }
    
    @Override
    public <T> T readObject(Class<T> cls) throws IOException, ClassNotFoundException {
        return cls.cast(readObject());
    }
    
    /**
     * 关闭输入流
     * 
     * @throws IOException IO异常
     */
    public void close() throws IOException {
        objectInputStream.close();
    }
}
