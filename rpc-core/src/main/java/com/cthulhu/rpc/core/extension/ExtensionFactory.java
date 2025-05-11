package com.cthulhu.rpc.core.extension;

/**
 * 扩展点加载器工厂，用于获取扩展加载器实例
 * 
 * @author cthulhu-rpc
 */
public class ExtensionFactory {
    
    /**
     * 获取指定类型的扩展加载器
     * 
     * @param type 扩展点类型
     * @param <T> 泛型类型
     * @return 扩展加载器
     */
    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
        // 实际实现将在后续开发中完成
        return null;
    }
}
