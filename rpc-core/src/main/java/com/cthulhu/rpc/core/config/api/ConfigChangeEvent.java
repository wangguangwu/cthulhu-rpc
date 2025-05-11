package com.cthulhu.rpc.core.config.api;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置变更事件
 * 
 * @author cthulhu-rpc
 */
public class ConfigChangeEvent {
    
    /**
     * 变更类型
     */
    public enum ChangeType {
        /**
         * 新增
         */
        ADDED,
        
        /**
         * 修改
         */
        MODIFIED,
        
        /**
         * 删除
         */
        DELETED
    }
    
    /**
     * 配置源
     */
    private final String source;
    
    /**
     * 变更的配置项
     */
    private final Map<String, ConfigChange> changes;
    
    /**
     * 构造函数
     * 
     * @param source 配置源
     */
    public ConfigChangeEvent(String source) {
        this.source = source;
        this.changes = new HashMap<>();
    }
    
    /**
     * 获取配置源
     * 
     * @return 配置源
     */
    public String getSource() {
        return source;
    }
    
    /**
     * 添加变更项
     * 
     * @param key 配置键
     * @param oldValue 旧值
     * @param newValue 新值
     * @param changeType 变更类型
     */
    public void addChange(String key, String oldValue, String newValue, ChangeType changeType) {
        changes.put(key, new ConfigChange(key, oldValue, newValue, changeType));
    }
    
    /**
     * 获取所有变更项
     * 
     * @return 变更项集合
     */
    public Map<String, ConfigChange> getChanges() {
        return changes;
    }
    
    /**
     * 配置变更项
     */
    public static class ConfigChange {
        private final String key;
        private final String oldValue;
        private final String newValue;
        private final ChangeType changeType;
        
        public ConfigChange(String key, String oldValue, String newValue, ChangeType changeType) {
            this.key = key;
            this.oldValue = oldValue;
            this.newValue = newValue;
            this.changeType = changeType;
        }
        
        public String getKey() {
            return key;
        }
        
        public String getOldValue() {
            return oldValue;
        }
        
        public String getNewValue() {
            return newValue;
        }
        
        public ChangeType getChangeType() {
            return changeType;
        }
    }
}
