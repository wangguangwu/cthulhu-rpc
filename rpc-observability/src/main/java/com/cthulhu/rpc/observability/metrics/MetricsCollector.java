package com.cthulhu.rpc.observability.metrics;

import com.cthulhu.rpc.core.extension.SPI;

/**
 * 指标收集器接口
 * 
 * @author cthulhu-rpc
 */
@SPI("default")
public interface MetricsCollector {
    
    /**
     * 记录计数器
     * 
     * @param name 指标名称
     * @param value 增加的值
     * @param tags 标签
     */
    void recordCounter(String name, long value, String... tags);
    
    /**
     * 记录计量器
     * 
     * @param name 指标名称
     * @param value 值
     * @param tags 标签
     */
    void recordGauge(String name, double value, String... tags);
    
    /**
     * 记录直方图
     * 
     * @param name 指标名称
     * @param value 值
     * @param tags 标签
     */
    void recordHistogram(String name, long value, String... tags);
    
    /**
     * 记录计时器
     * 
     * @param name 指标名称
     * @param timeInMillis 时间（毫秒）
     * @param tags 标签
     */
    void recordTimer(String name, long timeInMillis, String... tags);
}
