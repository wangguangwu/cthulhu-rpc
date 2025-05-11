package com.cthulhu.rpc.core.exporter;

import com.cthulhu.rpc.core.invoker.Invoker;

/**
 * 抽象服务导出者
 * 
 * @author wangguangwu
 * @param <T> 服务类型
 */
public abstract class AbstractExporter<T> implements Exporter<T> {
    
    /**
     * 服务调用器
     */
    private final Invoker<T> invoker;
    
    /**
     * 是否已注销
     */
    private volatile boolean unexported = false;
    
    public AbstractExporter(Invoker<T> invoker) {
        this.invoker = invoker;
    }
    
    /**
     * 获取服务调用器
     * 
     * @return 服务调用器
     */
    public Invoker<T> getInvoker() {
        return invoker;
    }
    
    /**
     * 注销服务
     */
    public void unexport() {
        if (unexported) {
            return;
        }
        unexported = true;
    }
    
    /**
     * 判断服务是否已注销
     * 
     * @return 是否已注销
     */
    public boolean isUnexported() {
        return unexported;
    }
}
