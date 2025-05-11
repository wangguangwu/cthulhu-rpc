package com.cthulhu.rpc.observability.tracing;

/**
 * 分布式追踪的Span接口
 * 
 * @author cthulhu-rpc
 */
public interface Span {
    
    /**
     * 设置标签
     * 
     * @param key 标签键
     * @param value 标签值
     * @return 当前Span
     */
    Span setTag(String key, String value);
    
    /**
     * 设置事件
     * 
     * @param name 事件名称
     * @return 当前Span
     */
    Span setEvent(String name);
    
    /**
     * 设置错误
     * 
     * @param throwable 异常
     * @return 当前Span
     */
    Span setError(Throwable throwable);
    
    /**
     * 结束当前Span
     */
    void finish();
    
    /**
     * 创建子Span
     * 
     * @param operationName 操作名称
     * @return 子Span
     */
    Span createChildSpan(String operationName);
}
