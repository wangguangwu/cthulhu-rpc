package com.cthulhu.rpc.core.extension;

/**
 * 扩展点配置类，定义扩展点的加载路径和规则
 * 
 * @author cthulhu-rpc
 */
public class ExtensionConfig {
    
    /**
     * 默认的扩展点配置文件路径
     */
    public static final String EXTENSION_DIRECTORY = "META-INF/extensions/";
    
    /**
     * 内部扩展点配置文件路径
     */
    public static final String INTERNAL_DIRECTORY = "META-INF/internal/";
    
    /**
     * 自定义扩展点配置文件路径
     */
    public static final String CUSTOM_DIRECTORY = "META-INF/custom/";
}
