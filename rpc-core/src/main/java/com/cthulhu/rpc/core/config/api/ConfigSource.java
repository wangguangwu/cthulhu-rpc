package com.cthulhu.rpc.core.config.api;

import java.util.Map;

/**
 * 配置源接口，用于从不同的配置源获取配置
 * 
 * @author cthulhu-rpc
 */
public interface ConfigSource {
    
    /**
     * 获取配置源名称
     * 
     * @return 配置源名称
     */
    String getName();
    
    /**
     * 获取配置项
     * 
     * @param key 配置键
     * @return 配置值，如果不存在则返回null
     */
    String getProperty(String key);
    
    /**
     * 获取所有配置项
     * 
     * @return 所有配置项
     */
    Map<String, String> getAllProperties();
    
    /**
     * 获取配置源优先级，数字越小优先级越高
     * 
     * @return 优先级
     */
    int getOrder();
}
