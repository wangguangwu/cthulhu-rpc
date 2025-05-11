package com.cthulhu.rpc.protocol;

import com.cthulhu.rpc.core.exception.RpcException;
import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.invoker.Invoker;
import com.cthulhu.rpc.core.exporter.Exporter;
import com.cthulhu.rpc.core.extension.SPI;

/**
 * Protocol 接口定义了 RPC 协议的基本行为
 * 所有协议实现必须实现此接口
 *
 * @author wangguangwu
 */
@SPI
public interface Protocol {
    /**
     * 获取默认端口
     *
     * @return 默认端口号
     */
    int getDefaultPort();

    /**
     * 暴露服务
     *
     * @param invoker 服务的执行体
     * @param <T>     服务的类型
     * @return Exporter 实例
     * @throws RpcException 当暴露服务失败时抛出
     */
    <T> Exporter<T> export(Invoker<T> invoker) throws RpcException;

    /**
     * 引用远程服务
     *
     * @param type 服务的类型
     * @param url  远程服务的地址
     * @param <T>  服务的类型
     * @return Invoker 实例
     * @throws RpcException 当引用服务失败时抛出
     */
    <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException;

    /**
     * 销毁协议
     */
    void destroy();
}
