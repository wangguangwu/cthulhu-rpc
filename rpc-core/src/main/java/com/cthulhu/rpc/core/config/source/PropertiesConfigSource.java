package com.cthulhu.rpc.core.config.source;

import com.cthulhu.rpc.core.config.api.ConfigSource;
import com.cthulhu.rpc.core.extension.Extension;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 属性文件配置源
 * 
 * @author cthulhu-rpc
 */
@Extension("properties")
public class PropertiesConfigSource implements ConfigSource {
    
    /**
     * 配置源优先级，数字越小优先级越高
     */
    private static final int ORDER = 300;
    
    /**
     * 属性文件路径
     */
    private final String path;
    
    /**
     * 属性对象
     */
    private final Properties properties = new Properties();
    
    /**
     * 构造函数
     * 
     * @param path 属性文件路径
     */
    public PropertiesConfigSource(String path) {
        this.path = path;
        loadProperties();
    }
    
    /**
     * 加载属性文件
     */
    private void loadProperties() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            // 日志记录异常信息
        }
    }
    
    @Override
    public String getName() {
        return "Properties[" + path + "]";
    }
    
    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    @Override
    public Map<String, String> getAllProperties() {
        Map<String, String> result = new HashMap<>();
        
        for (String key : properties.stringPropertyNames()) {
            result.put(key, properties.getProperty(key));
        }
        
        return result;
    }
    
    @Override
    public int getOrder() {
        return ORDER;
    }
}
