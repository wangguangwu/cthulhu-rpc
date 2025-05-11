package com.cthulhu.rpc.core.invoker;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.exception.RpcException;

/**
 * Invoker - 调用者
 * 代表一个可执行体，可向它发起调用
 */
public interface Invoker<T> {
    /**
     * 获取服务接口类型
     *
     * @return 服务接口类型
     */
    Class<T> getInterface();

    /**
     * 调用服务
     *
     * @param invocation 调用信息
     * @return 调用结果
     * @throws RpcException 当调用失败时抛出
     */
    Result invoke(Invocation invocation) throws RpcException;

    /**
     * 获取服务URL
     *
     * @return 服务URL
     */
    URL getUrl();

    /**
     * 判断服务是否可用
     *
     * @return 如果服务可用返回true，否则返回false
     */
    boolean isAvailable();

    /**
     * 销毁服务，释放资源
     */
    void destroy();
}
