package com.cthulhu.rpc.protocol.handler;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.extension.SPI;
import com.cthulhu.rpc.protocol.message.Request;
import com.cthulhu.rpc.protocol.message.Response;

/**
 * 协议处理器接口 - 负责处理协议的请求和响应
 *
 * @author wangguangwu
 */
@SPI
public interface ProtocolHandler {
    /**
     * 处理请求
     *
     * @param url 服务URL
     * @param request 请求消息
     * @return 响应消息
     */
    Response handle(URL url, Request request);
    
    /**
     * 连接到远程服务
     *
     * @param url 服务URL
     */
    void connect(URL url);
    
    /**
     * 断开与远程服务的连接
     *
     * @param url 服务URL
     */
    void disconnect(URL url);
}
