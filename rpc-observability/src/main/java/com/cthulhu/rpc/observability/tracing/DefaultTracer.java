package com.cthulhu.rpc.observability.tracing;

import com.cthulhu.rpc.core.extension.Extension;

/**
 * 默认的分布式追踪实现
 * 
 * @author cthulhu-rpc
 */
@Extension("default")
public class DefaultTracer implements Tracer {
    
    /**
     * 线程本地变量，用于存储当前活动的Span
     */
    private final ThreadLocal<Span> currentSpan = new ThreadLocal<>();
    
    @Override
    public Span createSpan(String operationName) {
        DefaultSpan span = new DefaultSpan(operationName);
        currentSpan.set(span);
        return span;
    }
    
    @Override
    public Span currentSpan() {
        return currentSpan.get();
    }
    
    @Override
    public void inject(Object carrier) {
        // 默认实现，暂不处理
    }
    
    @Override
    public Span extract(Object carrier, String operationName) {
        // 默认实现，直接创建新的Span
        return createSpan(operationName);
    }
    
    /**
     * 默认的Span实现
     */
    private class DefaultSpan implements Span {
        
        private final String operationName;
        
        public DefaultSpan(String operationName) {
            this.operationName = operationName;
            System.out.println("Span created: " + operationName);
        }
        
        @Override
        public Span setTag(String key, String value) {
            System.out.println("Span " + operationName + " set tag: " + key + "=" + value);
            return this;
        }
        
        @Override
        public Span setEvent(String name) {
            System.out.println("Span " + operationName + " set event: " + name);
            return this;
        }
        
        @Override
        public Span setError(Throwable throwable) {
            System.out.println("Span " + operationName + " set error: " + throwable.getMessage());
            return this;
        }
        
        @Override
        public void finish() {
            System.out.println("Span finished: " + operationName);
            if (currentSpan.get() == this) {
                currentSpan.remove();
            }
        }
        
        @Override
        public Span createChildSpan(String operationName) {
            DefaultSpan childSpan = new DefaultSpan(operationName);
            System.out.println("Child span created: " + operationName + " (parent: " + this.operationName + ")");
            return childSpan;
        }
    }
}
