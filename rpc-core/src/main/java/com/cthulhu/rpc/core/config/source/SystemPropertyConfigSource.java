package com.cthulhu.rpc.core.config.source;

import com.cthulhu.rpc.core.config.api.ConfigSource;
import com.cthulhu.rpc.core.extension.Extension;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 系统属性配置源
 * 
 * @author cthulhu-rpc
 */
@Extension("system")
public class SystemPropertyConfigSource implements ConfigSource {
    
    /**
     * 配置源优先级，数字越小优先级越高
     */
    private static final int ORDER = 100;
    
    @Override
    public String getName() {
        return "SystemProperty";
    }
    
    @Override
    public String getProperty(String key) {
        return System.getProperty(key);
    }
    
    @Override
    public Map<String, String> getAllProperties() {
        Properties properties = System.getProperties();
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
