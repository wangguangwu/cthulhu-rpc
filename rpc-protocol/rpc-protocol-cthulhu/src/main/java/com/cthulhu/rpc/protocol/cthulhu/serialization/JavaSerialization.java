package com.cthulhu.rpc.protocol.cthulhu.serialization;

import com.cthulhu.rpc.core.extension.SPI;
import com.cthulhu.rpc.protocol.constants.ProtocolConstants;
import com.cthulhu.rpc.protocol.serialization.ObjectInput;
import com.cthulhu.rpc.protocol.serialization.ObjectOutput;
import com.cthulhu.rpc.protocol.serialization.Serialization;

import java.io.IOException;

/**
 * Java 原生序列化实现
 * 
 * @author wangguangwu
 */
@SPI("java")
public class JavaSerialization implements Serialization {
    
    @Override
    public byte getContentTypeId() {
        return ProtocolConstants.SerializationType.JAVA;
    }
    
    @Override
    public String getContentType() {
        return "application/x-java-serialized-object";
    }
    
    @Override
    public ObjectOutput serialize() throws IOException {
        return new JavaObjectOutput();
    }
    
    @Override
    public ObjectInput deserialize() throws IOException {
        return new JavaObjectInput();
    }
}
