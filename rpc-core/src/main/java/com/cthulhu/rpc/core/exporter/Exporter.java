package com.cthulhu.rpc.core.exporter;

import com.cthulhu.rpc.core.invoker.Invoker;

/**
 * Exporter - 服务导出者接口
 * 
 * @author wangguangwu
 * @param <T> 服务类型
 */
public interface Exporter<T> {
    
    /**
     * 获取服务调用器
     * 
     * @return 服务调用器
     */
    Invoker<T> getInvoker();
    
    /**
     * 注销服务
     */
    void unexport();
}
