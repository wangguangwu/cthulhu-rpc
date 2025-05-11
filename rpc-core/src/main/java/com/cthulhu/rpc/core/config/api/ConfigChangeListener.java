package com.cthulhu.rpc.core.config.api;

/**
 * 配置变更监听器接口
 * 
 * @author cthulhu-rpc
 */
public interface ConfigChangeListener {
    
    /**
     * 配置变更事件处理
     * 
     * @param event 配置变更事件
     */
    void onChange(ConfigChangeEvent event);
}
