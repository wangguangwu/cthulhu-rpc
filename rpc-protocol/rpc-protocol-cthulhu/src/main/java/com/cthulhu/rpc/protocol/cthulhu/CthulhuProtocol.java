package com.cthulhu.rpc.protocol.cthulhu;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.exception.RpcException;
import com.cthulhu.rpc.core.exporter.Exporter;
import com.cthulhu.rpc.core.invoker.Invoker;
import com.cthulhu.rpc.protocol.Protocol;
import com.cthulhu.rpc.protocol.cthulhu.handler.CthulhuProtocolHandler;
import com.cthulhu.rpc.protocol.cthulhu.transport.CthulhuServer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cthulhu 协议实现
 * 
 * @author wangguangwu
 */
public class CthulhuProtocol implements Protocol {
    
    /**
     * 协议名称
     */
    public static final String NAME = "cthulhu";
    
    /**
     * 默认端口
     */
    private static final int DEFAULT_PORT = 20880;
    
    /**
     * 单例实例
     */
    private static final CthulhuProtocol INSTANCE = new CthulhuProtocol();
    
    /**
     * 服务器映射表
     */
    private final Map<String, CthulhuServer> serverMap = new ConcurrentHashMap<>();
    
    /**
     * 导出器映射表
     */
    private final Map<String, Exporter<?>> exporterMap = new ConcurrentHashMap<>();
    
    /**
     * 处理器映射表
     */
    private final Map<String, CthulhuProtocolHandler> handlerMap = new ConcurrentHashMap<>();
    
    private CthulhuProtocol() {
    }
    
    public static CthulhuProtocol getInstance() {
        return INSTANCE;
    }
    
    @Override
    public int getDefaultPort() {
        return DEFAULT_PORT;
    }
    
    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        // 获取服务URL
        URL url = invoker.getUrl();
        
        // 生成服务键
        String key = serviceKey(url);
        
        // 创建导出器
        CthulhuExporter<T> exporter = new CthulhuExporter<>(invoker, key);
        exporterMap.put(key, exporter);
        
        // 启动服务器
        openServer(url);
        
        // 注册服务调用器
        String address = url.getHost() + ":" + url.getPort();
        CthulhuProtocolHandler handler = handlerMap.get(address);
        handler.registerInvoker(key, invoker);
        
        return exporter;
    }
    
    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
        // 创建调用器
        return new CthulhuInvoker<T>(type, url);
    }
    
    @Override
    public void destroy() {
        // 关闭所有服务器
        for (CthulhuServer server : serverMap.values()) {
            try {
                server.close();
            } catch (Throwable t) {
                // 忽略异常
            }
        }
        serverMap.clear();
        
        // 注销所有导出器
        for (Exporter<?> exporter : exporterMap.values()) {
            try {
                exporter.unexport();
            } catch (Throwable t) {
                // 忽略异常
            }
        }
        exporterMap.clear();
        
        // 清空处理器
        handlerMap.clear();
    }
    
    /**
     * 获取服务键
     * 
     * @param url 服务URL
     * @return 服务键
     */
    private String serviceKey(URL url) {
        return url.getPath();
    }
    
    /**
     * 启动服务器
     * 
     * @param url 服务URL
     */
    private void openServer(URL url) {
        // 获取服务地址
        String address = url.getHost() + ":" + url.getPort();
        
        // 检查服务器是否已存在
        CthulhuServer server = serverMap.get(address);
        if (server == null) {
            // 创建处理器
            CthulhuProtocolHandler handler = new CthulhuProtocolHandler(url);
            handlerMap.put(address, handler);
            
            // 创建服务器
            server = new CthulhuServer(url, handler);
            
            // 启动服务器
            server.start();
            
            // 添加到映射表
            serverMap.put(address, server);
        }
    }
    
    /**
     * 注销服务调用器
     * 
     * @param address 服务地址
     * @param key 服务键
     */
    public void unregisterInvoker(String address, String key) {
        // 从导出器映射表中移除
        exporterMap.remove(key);
        
        // 从处理器中注销服务
        CthulhuProtocolHandler handler = handlerMap.get(address);
        if (handler != null) {
            handler.unregisterInvoker(key);
        }
        
        // 如果没有服务了，关闭服务器
        if (handler == null || handler.isEmpty()) {
            CthulhuServer server = serverMap.remove(address);
            if (server != null) {
                try {
                    server.close();
                } catch (Throwable t) {
                    // 忽略异常
                }
            }
            handlerMap.remove(address);
        }
    }
}
