package com.cthulhu.rpc.protocol.factory;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.extension.SPI;
import com.cthulhu.rpc.protocol.Protocol;

/**
 * 协议工厂接口 - 负责创建和管理协议实例
 *
 * @author wangguangwu
 */
@SPI
public interface ProtocolFactory {
    /**
     * 获取协议实例
     *
     * @param url 服务URL
     * @return 协议实例
     */
    Protocol getProtocol(URL url);
    
    /**
     * 根据协议名获取协议实例
     *
     * @param protocolName 协议名
     * @return 协议实例
     */
    Protocol getProtocol(String protocolName);
}
