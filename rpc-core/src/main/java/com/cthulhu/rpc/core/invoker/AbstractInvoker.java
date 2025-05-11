package com.cthulhu.rpc.core.invoker;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.exception.RpcException;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 抽象调用者
 * 
 * @author wangguangwu
 * @param <T> 服务类型
 */
public abstract class AbstractInvoker<T> implements Invoker<T> {
    
    /**
     * 服务接口类型
     */
    private final Class<T> type;
    
    /**
     * 服务URL
     */
    private final URL url;
    
    /**
     * 是否已销毁
     */
    private final AtomicBoolean destroyed = new AtomicBoolean(false);
    
    public AbstractInvoker(Class<T> type, URL url) {
        this.type = type;
        this.url = url;
    }
    
    @Override
    public Class<T> getInterface() {
        return type;
    }
    
    @Override
    public URL getUrl() {
        return url;
    }
    
    @Override
    public boolean isAvailable() {
        return !destroyed.get();
    }
    
    @Override
    public void destroy() {
        if (!destroyed.compareAndSet(false, true)) {
            return;
        }
    }
    
    @Override
    public Result invoke(Invocation invocation) throws RpcException {
        if (destroyed.get()) {
            throw new RpcException("调用器已销毁");
        }
        
        try {
            return doInvoke(invocation);
        } catch (Throwable e) {
            if (e instanceof RpcException) {
                throw (RpcException) e;
            } else {
                throw new RpcException(e);
            }
        }
    }
    
    /**
     * 执行调用
     * 
     * @param invocation 调用信息
     * @return 调用结果
     * @throws Throwable 调用异常
     */
    protected abstract Result doInvoke(Invocation invocation) throws Throwable;
}
