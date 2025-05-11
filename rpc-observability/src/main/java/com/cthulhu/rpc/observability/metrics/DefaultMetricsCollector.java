package com.cthulhu.rpc.observability.metrics;

import com.cthulhu.rpc.core.extension.Extension;

/**
 * 默认的指标收集器实现
 * 
 * @author cthulhu-rpc
 */
@Extension("default")
public class DefaultMetricsCollector implements MetricsCollector {
    
    @Override
    public void recordCounter(String name, long value, String... tags) {
        // 默认实现，仅记录日志
        System.out.println("Counter: " + name + ", value: " + value);
    }
    
    @Override
    public void recordGauge(String name, double value, String... tags) {
        // 默认实现，仅记录日志
        System.out.println("Gauge: " + name + ", value: " + value);
    }
    
    @Override
    public void recordHistogram(String name, long value, String... tags) {
        // 默认实现，仅记录日志
        System.out.println("Histogram: " + name + ", value: " + value);
    }
    
    @Override
    public void recordTimer(String name, long timeInMillis, String... tags) {
        // 默认实现，仅记录日志
        System.out.println("Timer: " + name + ", value: " + timeInMillis + "ms");
    }
}
