package com.cthulhu.rpc.protocol.message;

/**
 * 响应消息接口 - 定义RPC响应的结构
 *
 * @author wangguangwu
 */
public interface Response extends Message {
    /**
     * 响应消息类型
     */
    byte RESPONSE_TYPE = 2;
    
    /**
     * 正常响应状态
     */
    byte OK = 20;
    
    /**
     * 客户端错误状态
     */
    byte CLIENT_ERROR = 40;
    
    /**
     * 服务端错误状态
     */
    byte SERVER_ERROR = 50;
    
    /**
     * 获取响应状态码
     *
     * @return 状态码
     */
    byte getStatus();
    
    /**
     * 设置响应状态码
     *
     * @param status 状态码
     */
    void setStatus(byte status);
    
    /**
     * 获取响应结果
     *
     * @return 响应结果
     */
    Object getResult();
    
    /**
     * 设置响应结果
     *
     * @param result 响应结果
     */
    void setResult(Object result);
    
    /**
     * 获取异常信息
     *
     * @return 异常信息
     */
    Throwable getException();
    
    /**
     * 设置异常信息
     *
     * @param exception 异常信息
     */
    void setException(Throwable exception);
}
