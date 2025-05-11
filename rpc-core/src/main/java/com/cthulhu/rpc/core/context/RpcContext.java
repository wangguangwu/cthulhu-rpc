package com.cthulhu.rpc.core.context;

import java.util.HashMap;
import java.util.Map;

/**
 * RPC上下文，用于在调用链中传递信息
 * 
 * @author cthulhu-rpc
 */
public class RpcContext {
    
    /**
     * 线程本地变量，存储当前线程的RPC上下文
     */
    private static final ThreadLocal<RpcContext> LOCAL = new ThreadLocal<RpcContext>() {
        @Override
        protected RpcContext initialValue() {
            return new RpcContext();
        }
    };
    
    /**
     * 附加属性
     */
    private final Map<String, Object> attachments = new HashMap<>();
    
    /**
     * 获取当前线程的RPC上下文
     * 
     * @return RPC上下文
     */
    public static RpcContext getContext() {
        return LOCAL.get();
    }
    
    /**
     * 移除当前线程的RPC上下文
     */
    public static void removeContext() {
        LOCAL.remove();
    }
    
    /**
     * 设置附加属性
     * 
     * @param key 键
     * @param value 值
     * @return 当前上下文
     */
    public RpcContext setAttachment(String key, Object value) {
        attachments.put(key, value);
        return this;
    }
    
    /**
     * 获取附加属性
     * 
     * @param key 键
     * @return 值
     */
    @SuppressWarnings("unchecked")
    public <T> T getAttachment(String key) {
        return (T) attachments.get(key);
    }
    
    /**
     * 移除附加属性
     * 
     * @param key 键
     * @return 移除的值
     */
    public Object removeAttachment(String key) {
        return attachments.remove(key);
    }
    
    /**
     * 获取所有附加属性
     * 
     * @return 所有附加属性
     */
    public Map<String, Object> getAttachments() {
        return new HashMap<>(attachments);
    }
}
