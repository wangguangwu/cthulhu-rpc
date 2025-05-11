package com.cthulhu.rpc.protocol.cthulhu.message;

import com.cthulhu.rpc.protocol.message.Request;

/**
 * Cthulhu 协议请求消息
 * 
 * @author wangguangwu
 */
public class CthulhuRequest extends CthulhuMessage implements Request {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 请求类型：单向请求（不需要响应）
     */
    public static final byte ONE_WAY = 1;
    
    /**
     * 请求类型：双向请求（需要响应）
     */
    public static final byte TWO_WAY = 2;
    
    /**
     * 协议版本
     */
    private String version;
    
    /**
     * 接口名称
     */
    private String interfaceName;
    
    /**
     * 方法名称
     */
    private String methodName;
    
    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;
    
    /**
     * 参数值
     */
    private Object[] arguments;
    
    /**
     * 是否单向请求
     */
    private boolean oneWay = false;
    
    public CthulhuRequest() {
        setMessageType(TWO_WAY);
    }
    
    @Override
    public String getInterfaceName() {
        return interfaceName;
    }
    
    /**
     * 设置接口名称
     * 
     * @param interfaceName 接口名称
     */
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
    
    @Override
    public String getMethodName() {
        return methodName;
    }
    
    /**
     * 设置方法名称
     * 
     * @param methodName 方法名称
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
    @Override
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }
    
    /**
     * 设置参数类型
     * 
     * @param parameterTypes 参数类型
     */
    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
    
    @Override
    public Object[] getArguments() {
        return arguments;
    }
    
    /**
     * 设置参数值
     * 
     * @param arguments 参数值
     */
    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
    
    /**
     * 获取协议版本
     * 
     * @return 协议版本
     */
    public String getVersion() {
        return version;
    }
    
    /**
     * 设置协议版本
     * 
     * @param version 协议版本
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
    /**
     * 判断是否单向请求
     * 
     * @return 如果是单向请求返回true，否则返回false
     */
    public boolean isOneWay() {
        return oneWay;
    }
    
    /**
     * 设置是否单向请求
     * 
     * @param oneWay 是否单向请求
     */
    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
        setMessageType(oneWay ? ONE_WAY : TWO_WAY);
    }
}
