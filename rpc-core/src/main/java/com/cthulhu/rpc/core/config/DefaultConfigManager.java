package com.cthulhu.rpc.core.config;

import com.cthulhu.rpc.core.config.api.ConfigChangeEvent;
import com.cthulhu.rpc.core.config.api.ConfigChangeListener;
import com.cthulhu.rpc.core.config.api.ConfigManager;
import com.cthulhu.rpc.core.config.api.ConfigSource;
import com.cthulhu.rpc.core.extension.SPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 默认配置管理器实现
 * 
 * @author cthulhu-rpc
 */
@SPI("default")
public class DefaultConfigManager implements ConfigManager {
    
    /**
     * 配置源列表
     */
    private final List<ConfigSource> configSources = new ArrayList<>();
    
    /**
     * 配置变更监听器列表
     */
    private final List<ConfigChangeListener> listeners = new CopyOnWriteArrayList<>();
    
    @Override
    public String getConfig(String key) {
        // 实际实现将在后续开发中完成
        return null;
    }
    
    @Override
    public String getConfig(String key, String defaultValue) {
        // 实际实现将在后续开发中完成
        return null;
    }
    
    @Override
    public <T> T getConfig(String key, Class<T> clazz) {
        // 实际实现将在后续开发中完成
        return null;
    }
    
    @Override
    public <T> T getConfig(String key, Class<T> clazz, T defaultValue) {
        // 实际实现将在后续开发中完成
        return null;
    }
    
    @Override
    public Map<String, String> getConfigsByPrefix(String prefix) {
        // 实际实现将在后续开发中完成
        return null;
    }
    
    @Override
    public void addListener(ConfigChangeListener listener) {
        listeners.add(listener);
    }
    
    @Override
    public void removeListener(ConfigChangeListener listener) {
        listeners.remove(listener);
    }
    
    /**
     * 添加配置源
     * 
     * @param configSource 配置源
     */
    public void addConfigSource(ConfigSource configSource) {
        configSources.add(configSource);
        // 按优先级排序
        configSources.sort((o1, o2) -> Integer.compare(o1.getOrder(), o2.getOrder()));
    }
}
