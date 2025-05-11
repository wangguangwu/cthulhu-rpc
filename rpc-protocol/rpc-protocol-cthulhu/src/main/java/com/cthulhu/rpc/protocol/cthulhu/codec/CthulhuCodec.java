package com.cthulhu.rpc.protocol.cthulhu.codec;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.extension.SPI;
import com.cthulhu.rpc.protocol.codec.Codec;
import com.cthulhu.rpc.protocol.constants.ProtocolConstants;
import com.cthulhu.rpc.protocol.cthulhu.message.CthulhuMessage;
import com.cthulhu.rpc.protocol.cthulhu.message.CthulhuRequest;
import com.cthulhu.rpc.protocol.cthulhu.message.CthulhuResponse;
import com.cthulhu.rpc.protocol.cthulhu.serialization.JavaObjectInput;
import com.cthulhu.rpc.protocol.cthulhu.serialization.JavaObjectOutput;
import com.cthulhu.rpc.protocol.cthulhu.serialization.SerializationFactory;
import com.cthulhu.rpc.protocol.exception.ProtocolException;
import com.cthulhu.rpc.protocol.serialization.ObjectInput;
import com.cthulhu.rpc.protocol.serialization.ObjectOutput;
import com.cthulhu.rpc.protocol.serialization.Serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Cthulhu 协议编解码器
 * 
 * @author wangguangwu
 */
@SPI("cthulhu")
public class CthulhuCodec implements Codec {
    
    /**
     * 魔数，用于标识 Cthulhu 协议
     */
    private static final short MAGIC = (short) 0xC7C7;
    
    /**
     * 版本号
     */
    private static final byte VERSION = ProtocolConstants.Version.CURRENT_VERSION;
    
    /**
     * 请求消息类型
     */
    private static final byte REQUEST = ProtocolConstants.MessageType.REQUEST;
    
    /**
     * 响应消息类型
     */
    private static final byte RESPONSE = ProtocolConstants.MessageType.RESPONSE;
    
    /**
     * 心跳请求消息类型
     */
    private static final byte HEARTBEAT_REQUEST = ProtocolConstants.MessageType.HEARTBEAT_REQUEST;
    
    /**
     * 心跳响应消息类型
     */
    private static final byte HEARTBEAT_RESPONSE = ProtocolConstants.MessageType.HEARTBEAT_RESPONSE;
    
    /**
     * 默认序列化类型
     */
    private static final byte DEFAULT_SERIALIZATION_TYPE = ProtocolConstants.SerializationType.JAVA;
    
    @Override
    public void encode(URL url, OutputStream outputStream, Object message) throws IOException {
        if (message instanceof CthulhuMessage) {
            CthulhuMessage cthulhuMessage = (CthulhuMessage) message;
            
            // 写入魔数
            writeShort(outputStream, MAGIC);
            
            // 写入版本号
            outputStream.write(VERSION);
            
            // 写入消息类型
            byte messageType;
            if (message instanceof CthulhuRequest) {
                messageType = REQUEST;
            } else if (message instanceof CthulhuResponse) {
                messageType = RESPONSE;
            } else {
                messageType = HEARTBEAT_REQUEST;
            }
            outputStream.write(messageType);
            
            // 写入序列化类型
            byte serializationType = DEFAULT_SERIALIZATION_TYPE;
            outputStream.write(serializationType);
            
            // 写入消息ID
            writeLong(outputStream, cthulhuMessage.getId());
            
            // 写入消息体
            if (message instanceof CthulhuRequest) {
                encodeRequest(outputStream, (CthulhuRequest) message, serializationType);
            } else if (message instanceof CthulhuResponse) {
                encodeResponse(outputStream, (CthulhuResponse) message, serializationType);
            }
            
            // 刷新输出流
            outputStream.flush();
        } else {
            throw new ProtocolException("不支持的消息类型: " + message.getClass().getName());
        }
    }
    
    @Override
    public Object decode(URL url, InputStream inputStream) throws IOException {
        // 读取魔数
        short magic = readShort(inputStream);
        if (magic != MAGIC) {
            throw new ProtocolException("无效的魔数: " + magic);
        }
        
        // 读取版本号
        byte version = (byte) inputStream.read();
        if (version != VERSION) {
            throw new ProtocolException("不支持的版本: " + version);
        }
        
        // 读取消息类型
        byte messageType = (byte) inputStream.read();
        
        // 读取序列化类型
        byte serializationType = (byte) inputStream.read();
        
        // 读取消息ID
        long id = readLong(inputStream);
        
        // 根据消息类型解码消息体
        if (messageType == REQUEST) {
            return decodeRequest(inputStream, id, serializationType);
        } else if (messageType == RESPONSE) {
            return decodeResponse(inputStream, id, serializationType);
        } else if (messageType == HEARTBEAT_REQUEST || messageType == HEARTBEAT_RESPONSE) {
            // 心跳消息暂不处理
            return null;
        } else {
            throw new ProtocolException("未知的消息类型: " + messageType);
        }
    }
    
    /**
     * 编码请求消息
     * 
     * @param outputStream 输出流
     * @param request 请求消息
     * @param serializationType 序列化类型
     * @throws IOException IO异常
     */
    private void encodeRequest(OutputStream outputStream, CthulhuRequest request, byte serializationType) throws IOException {
        // 写入接口名称
        writeString(outputStream, request.getInterfaceName());
        
        // 写入方法名称
        writeString(outputStream, request.getMethodName());
        
        // 写入参数类型
        Class<?>[] parameterTypes = request.getParameterTypes();
        if (parameterTypes == null) {
            writeInt(outputStream, 0);
        } else {
            writeInt(outputStream, parameterTypes.length);
            for (Class<?> parameterType : parameterTypes) {
                writeString(outputStream, parameterType.getName());
            }
        }
        
        // 写入参数值
        Object[] arguments = request.getArguments();
        if (arguments == null) {
            writeInt(outputStream, 0);
        } else {
            writeInt(outputStream, arguments.length);
            
            // 使用序列化工具将参数值序列化为字节数组
            Serialization serialization = SerializationFactory.getSerialization(serializationType);
            for (Object argument : arguments) {
                if (argument == null) {
                    writeInt(outputStream, 0);
                } else {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutput objectOutput = serialization.serialize();
                    objectOutput.writeObject(argument);
                    objectOutput.flushBuffer();
                    
                    byte[] bytes = ((JavaObjectOutput) objectOutput).toByteArray();
                    writeInt(outputStream, bytes.length);
                    outputStream.write(bytes);
                    
                    ((JavaObjectOutput) objectOutput).close();
                }
            }
        }
    }
    
    /**
     * 解码请求消息
     * 
     * @param inputStream 输入流
     * @param id 消息ID
     * @param serializationType 序列化类型
     * @return 请求消息
     * @throws IOException IO异常
     */
    private CthulhuRequest decodeRequest(InputStream inputStream, long id, byte serializationType) throws IOException {
        CthulhuRequest request = new CthulhuRequest();
        request.setId(id);
        
        // 读取接口名称
        request.setInterfaceName(readString(inputStream));
        
        // 读取方法名称
        request.setMethodName(readString(inputStream));
        
        // 读取参数类型
        int parameterTypeCount = readInt(inputStream);
        if (parameterTypeCount > 0) {
            Class<?>[] parameterTypes = new Class<?>[parameterTypeCount];
            for (int i = 0; i < parameterTypeCount; i++) {
                String parameterTypeName = readString(inputStream);
                try {
                    parameterTypes[i] = Class.forName(parameterTypeName);
                } catch (ClassNotFoundException e) {
                    throw new ProtocolException("找不到参数类型: " + parameterTypeName, e);
                }
            }
            request.setParameterTypes(parameterTypes);
        }
        
        // 读取参数值
        int argumentCount = readInt(inputStream);
        if (argumentCount > 0) {
            Object[] arguments = new Object[argumentCount];
            
            // 使用反序列化工具将字节数组反序列化为参数值
            Serialization serialization = SerializationFactory.getSerialization(serializationType);
            for (int i = 0; i < argumentCount; i++) {
                int length = readInt(inputStream);
                if (length > 0) {
                    byte[] bytes = new byte[length];
                    int read = 0;
                    while (read < length) {
                        int count = inputStream.read(bytes, read, length - read);
                        if (count == -1) {
                            break;
                        }
                        read += count;
                    }
                    
                    ObjectInput objectInput = new JavaObjectInput(bytes);
                    try {
                        arguments[i] = objectInput.readObject();
                    } catch (ClassNotFoundException e) {
                        throw new ProtocolException("反序列化参数失败", e);
                    } finally {
                        ((JavaObjectInput) objectInput).close();
                    }
                }
            }
            request.setArguments(arguments);
        }
        
        return request;
    }
    
    /**
     * 编码响应消息
     * 
     * @param outputStream 输出流
     * @param response 响应消息
     * @param serializationType 序列化类型
     * @throws IOException IO异常
     */
    private void encodeResponse(OutputStream outputStream, CthulhuResponse response, byte serializationType) throws IOException {
        // 写入响应状态
        outputStream.write(response.getStatus());
        
        // 写入响应结果或异常
        if (response.getStatus() == CthulhuResponse.OK) {
            // 写入响应结果
            Object result = response.getResult();
            if (result == null) {
                writeInt(outputStream, 0);
            } else {
                // 使用序列化工具将响应结果序列化为字节数组
                Serialization serialization = SerializationFactory.getSerialization(serializationType);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutput objectOutput = serialization.serialize();
                objectOutput.writeObject(result);
                objectOutput.flushBuffer();
                
                byte[] bytes = ((JavaObjectOutput) objectOutput).toByteArray();
                writeInt(outputStream, bytes.length);
                outputStream.write(bytes);
                
                ((JavaObjectOutput) objectOutput).close();
            }
        } else {
            // 写入异常信息
            Throwable exception = response.getException();
            if (exception != null) {
                writeString(outputStream, exception.getClass().getName());
                writeString(outputStream, exception.getMessage());
            } else {
                writeString(outputStream, "");
                writeString(outputStream, "");
            }
        }
    }
    
    /**
     * 解码响应消息
     * 
     * @param inputStream 输入流
     * @param id 消息ID
     * @param serializationType 序列化类型
     * @return 响应消息
     * @throws IOException IO异常
     */
    private CthulhuResponse decodeResponse(InputStream inputStream, long id, byte serializationType) throws IOException {
        CthulhuResponse response = new CthulhuResponse();
        response.setId(id);
        
        // 读取响应状态
        byte status = (byte) inputStream.read();
        response.setStatus(status);
        
        // 读取响应结果或异常
        if (status == CthulhuResponse.OK) {
            // 读取响应结果
            int length = readInt(inputStream);
            if (length > 0) {
                // 使用反序列化工具将字节数组反序列化为响应结果
                byte[] bytes = new byte[length];
                int read = 0;
                while (read < length) {
                    int count = inputStream.read(bytes, read, length - read);
                    if (count == -1) {
                        break;
                    }
                    read += count;
                }
                
                Serialization serialization = SerializationFactory.getSerialization(serializationType);
                ObjectInput objectInput = new JavaObjectInput(bytes);
                try {
                    Object result = objectInput.readObject();
                    response.setResult(result);
                } catch (ClassNotFoundException e) {
                    throw new ProtocolException("反序列化结果失败", e);
                } finally {
                    ((JavaObjectInput) objectInput).close();
                }
            }
        } else {
            // 读取异常信息
            String exceptionClassName = readString(inputStream);
            String exceptionMessage = readString(inputStream);
            
            if (!exceptionClassName.isEmpty()) {
                try {
                    Class<?> exceptionClass = Class.forName(exceptionClassName);
                    Throwable exception = (Throwable) exceptionClass.getConstructor(String.class).newInstance(exceptionMessage);
                    response.setException(exception);
                } catch (Exception e) {
                    response.setException(new ProtocolException("解码异常失败: " + exceptionClassName + ": " + exceptionMessage, e));
                }
            }
        }
        
        return response;
    }
    
    /**
     * 写入短整型
     * 
     * @param outputStream 输出流
     * @param value 短整型值
     * @throws IOException IO异常
     */
    private void writeShort(OutputStream outputStream, short value) throws IOException {
        outputStream.write((value >> 8) & 0xFF);
        outputStream.write(value & 0xFF);
    }
    
    /**
     * 读取短整型
     * 
     * @param inputStream 输入流
     * @return 短整型值
     * @throws IOException IO异常
     */
    private short readShort(InputStream inputStream) throws IOException {
        return (short) (((inputStream.read() & 0xFF) << 8) | (inputStream.read() & 0xFF));
    }
    
    /**
     * 写入整型
     * 
     * @param outputStream 输出流
     * @param value 整型值
     * @throws IOException IO异常
     */
    private void writeInt(OutputStream outputStream, int value) throws IOException {
        outputStream.write((value >> 24) & 0xFF);
        outputStream.write((value >> 16) & 0xFF);
        outputStream.write((value >> 8) & 0xFF);
        outputStream.write(value & 0xFF);
    }
    
    /**
     * 读取整型
     * 
     * @param inputStream 输入流
     * @return 整型值
     * @throws IOException IO异常
     */
    private int readInt(InputStream inputStream) throws IOException {
        return ((inputStream.read() & 0xFF) << 24)
                | ((inputStream.read() & 0xFF) << 16)
                | ((inputStream.read() & 0xFF) << 8)
                | (inputStream.read() & 0xFF);
    }
    
    /**
     * 写入长整型
     * 
     * @param outputStream 输出流
     * @param value 长整型值
     * @throws IOException IO异常
     */
    private void writeLong(OutputStream outputStream, long value) throws IOException {
        outputStream.write((int) ((value >> 56) & 0xFF));
        outputStream.write((int) ((value >> 48) & 0xFF));
        outputStream.write((int) ((value >> 40) & 0xFF));
        outputStream.write((int) ((value >> 32) & 0xFF));
        outputStream.write((int) ((value >> 24) & 0xFF));
        outputStream.write((int) ((value >> 16) & 0xFF));
        outputStream.write((int) ((value >> 8) & 0xFF));
        outputStream.write((int) (value & 0xFF));
    }
    
    /**
     * 读取长整型
     * 
     * @param inputStream 输入流
     * @return 长整型值
     * @throws IOException IO异常
     */
    private long readLong(InputStream inputStream) throws IOException {
        return ((long) (inputStream.read() & 0xFF) << 56)
                | ((long) (inputStream.read() & 0xFF) << 48)
                | ((long) (inputStream.read() & 0xFF) << 40)
                | ((long) (inputStream.read() & 0xFF) << 32)
                | ((long) (inputStream.read() & 0xFF) << 24)
                | ((long) (inputStream.read() & 0xFF) << 16)
                | ((long) (inputStream.read() & 0xFF) << 8)
                | (inputStream.read() & 0xFF);
    }
    
    /**
     * 写入字符串
     * 
     * @param outputStream 输出流
     * @param value 字符串值
     * @throws IOException IO异常
     */
    private void writeString(OutputStream outputStream, String value) throws IOException {
        if (value == null) {
            writeInt(outputStream, 0);
        } else {
            byte[] bytes = value.getBytes("UTF-8");
            writeInt(outputStream, bytes.length);
            outputStream.write(bytes);
        }
    }
    
    /**
     * 读取字符串
     * 
     * @param inputStream 输入流
     * @return 字符串值
     * @throws IOException IO异常
     */
    private String readString(InputStream inputStream) throws IOException {
        int length = readInt(inputStream);
        if (length <= 0) {
            return "";
        }
        
        byte[] bytes = new byte[length];
        int read = 0;
        while (read < length) {
            int count = inputStream.read(bytes, read, length - read);
            if (count == -1) {
                break;
            }
            read += count;
        }
        
        return new String(bytes, 0, read, "UTF-8");
    }
}
