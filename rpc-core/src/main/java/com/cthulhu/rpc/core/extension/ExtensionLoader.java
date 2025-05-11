package com.cthulhu.rpc.core.extension;

/**
 * 扩展点加载器接口
 * 
 * @author cthulhu-rpc
 */
public interface ExtensionLoader<T> {
    
    /**
     * 获取默认的扩展实现
     * 
     * @return 默认的扩展实现
     */
    T getDefaultExtension();
    
    /**
     * 根据名称获取扩展实现
     * 
     * @param name 扩展名称
     * @return 扩展实现
     */
    T getExtension(String name);
    
    /**
     * 获取所有的扩展实现
     * 
     * @return 所有的扩展实现
     */
    java.util.List<T> getAllExtensions();
}
