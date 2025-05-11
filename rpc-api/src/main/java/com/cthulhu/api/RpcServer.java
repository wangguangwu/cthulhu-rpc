package com.cthulhu.api;

/**
 * RPC服务器接口
 */
public interface RpcServer {
    
    /**
     * 启动服务器
     */
    void start();
    
    /**
     * 注册服务
     *
     * @param serviceBean 服务实现对象
     */
    void registerService(Object serviceBean);
    
    /**
     * 注册服务
     *
     * @param serviceInterface 服务接口类
     * @param serviceBean     服务实现对象
     */
    void registerService(Class<?> serviceInterface, Object serviceBean);
    
    /**
     * 注册服务
     *
     * @param serviceInterface 服务接口类
     * @param serviceBean     服务实现对象
     * @param version         服务版本号
     */
    void registerService(Class<?> serviceInterface, Object serviceBean, String version);
    
    /**
     * 注册服务
     *
     * @param serviceInterface 服务接口类
     * @param serviceBean     服务实现对象
     * @param version         服务版本号
     * @param group           服务分组
     */
    void registerService(Class<?> serviceInterface, Object serviceBean, String version, String group);
    
    /**
     * 停止服务器
     */
    void stop();
}
