package com.cthulhu.rpc.protocol.message;

/**
 * 请求消息接口 - 定义RPC请求的结构
 *
 * @author wangguangwu
 */
public interface Request extends Message {
    /**
     * 请求消息类型
     */
    byte REQUEST_TYPE = 1;
    
    /**
     * 获取版本号
     *
     * @return 版本号
     */
    String getVersion();
    
    /**
     * 获取接口名
     *
     * @return 接口名
     */
    String getInterfaceName();
    
    /**
     * 获取方法名
     *
     * @return 方法名
     */
    String getMethodName();
    
    /**
     * 获取参数类型
     *
     * @return 参数类型数组
     */
    Class<?>[] getParameterTypes();
    
    /**
     * 获取参数值
     *
     * @return 参数值数组
     */
    Object[] getArguments();
    
    /**
     * 是否是单向请求（不需要响应）
     *
     * @return 如果是单向请求返回true，否则返回false
     */
    boolean isOneWay();
    
    /**
     * 是否是事件消息
     *
     * @return 如果是事件消息返回true，否则返回false
     */
    boolean isEvent();
}
