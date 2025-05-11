package com.cthulhu.rpc.observability.tracing;

import com.cthulhu.rpc.core.extension.SPI;

/**
 * 分布式追踪接口
 * 
 * @author cthulhu-rpc
 */
@SPI("default")
public interface Tracer {
    
    /**
     * 创建一个新的Span
     * 
     * @param operationName 操作名称
     * @return Span对象
     */
    Span createSpan(String operationName);
    
    /**
     * 获取当前活动的Span
     * 
     * @return 当前活动的Span
     */
    Span currentSpan();
    
    /**
     * 将当前Span的上下文注入到载体中
     * 
     * @param carrier 载体
     */
    void inject(Object carrier);
    
    /**
     * 从载体中提取Span上下文并创建一个新的子Span
     * 
     * @param carrier 载体
     * @param operationName 操作名称
     * @return 新的子Span
     */
    Span extract(Object carrier, String operationName);
}
