package com.cthulhu.rpc.core.config.api;

import java.util.Map;

/**
 * 配置管理器接口，负责管理所有配置项
 * 
 * @author cthulhu-rpc
 */
public interface ConfigManager {
    
    /**
     * 获取配置项
     * 
     * @param key 配置键
     * @return 配置值
     */
    String getConfig(String key);
    
    /**
     * 获取配置项，如果不存在则返回默认值
     * 
     * @param key 配置键
     * @param defaultValue 默认值
     * @return 配置值
     */
    String getConfig(String key, String defaultValue);
    
    /**
     * 获取指定类型的配置项
     * 
     * @param key 配置键
     * @param clazz 配置值类型
     * @param <T> 泛型类型
     * @return 配置值
     */
    <T> T getConfig(String key, Class<T> clazz);
    
    /**
     * 获取指定类型的配置项，如果不存在则返回默认值
     * 
     * @param key 配置键
     * @param clazz 配置值类型
     * @param defaultValue 默认值
     * @param <T> 泛型类型
     * @return 配置值
     */
    <T> T getConfig(String key, Class<T> clazz, T defaultValue);
    
    /**
     * 获取指定前缀的所有配置项
     * 
     * @param prefix 前缀
     * @return 配置项集合
     */
    Map<String, String> getConfigsByPrefix(String prefix);
    
    /**
     * 添加配置变更监听器
     * 
     * @param listener 监听器
     */
    void addListener(ConfigChangeListener listener);
    
    /**
     * 移除配置变更监听器
     * 
     * @param listener 监听器
     */
    void removeListener(ConfigChangeListener listener);
}
