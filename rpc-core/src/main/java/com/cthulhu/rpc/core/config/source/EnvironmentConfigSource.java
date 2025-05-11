package com.cthulhu.rpc.core.config.source;

import com.cthulhu.rpc.core.config.api.ConfigSource;
import com.cthulhu.rpc.core.extension.Extension;

import java.util.HashMap;
import java.util.Map;

/**
 * 环境变量配置源
 * 
 * @author cthulhu-rpc
 */
@Extension("environment")
public class EnvironmentConfigSource implements ConfigSource {
    
    /**
     * 配置源优先级，数字越小优先级越高
     */
    private static final int ORDER = 200;
    
    @Override
    public String getName() {
        return "Environment";
    }
    
    @Override
    public String getProperty(String key) {
        return System.getenv(key);
    }
    
    @Override
    public Map<String, String> getAllProperties() {
        return new HashMap<>(System.getenv());
    }
    
    @Override
    public int getOrder() {
        return ORDER;
    }
}
