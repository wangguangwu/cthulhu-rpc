package com.cthulhu.api;

/**
 * RPC客户端接口
 */
public interface RpcClient {
    
    /**
     * 获取远程服务代理对象
     *
     * @param interfaceClass 服务接口类
     * @param <T>           服务接口类型
     * @return 服务代理对象
     */
    <T> T getProxy(Class<T> interfaceClass);
    
    /**
     * 获取远程服务代理对象
     *
     * @param interfaceClass 服务接口类
     * @param version        服务版本号
     * @param <T>           服务接口类型
     * @return 服务代理对象
     */
    <T> T getProxy(Class<T> interfaceClass, String version);
    
    /**
     * 获取远程服务代理对象
     *
     * @param interfaceClass 服务接口类
     * @param version        服务版本号
     * @param group          服务分组
     * @param <T>           服务接口类型
     * @return 服务代理对象
     */
    <T> T getProxy(Class<T> interfaceClass, String version, String group);
    
    /**
     * 关闭客户端
     */
    void shutdown();
}
