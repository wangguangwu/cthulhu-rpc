package com.cthulhu.rpc.core.extension;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 扩展点加载器的默认实现
 * 
 * @author cthulhu-rpc
 * @param <T> 扩展点类型
 */
public class DefaultExtensionLoader<T> implements ExtensionLoader<T> {
    
    /**
     * 扩展点类型
     */
    private final Class<T> type;
    
    /**
     * 扩展点实例缓存
     */
    private final ConcurrentHashMap<String, T> extensionInstances = new ConcurrentHashMap<>();
    
    /**
     * 构造函数
     * 
     * @param type 扩展点类型
     */
    public DefaultExtensionLoader(Class<T> type) {
        this.type = type;
    }
    
    @Override
    public T getDefaultExtension() {
        // 实际实现将在后续开发中完成
        return null;
    }
    
    @Override
    public T getExtension(String name) {
        // 实际实现将在后续开发中完成
        return null;
    }
    
    @Override
    public List<T> getAllExtensions() {
        // 实际实现将在后续开发中完成
        return null;
    }
}
