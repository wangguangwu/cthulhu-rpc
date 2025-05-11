package com.cthulhu.rpc.core.invoker;

/**
 * Result - 调用结果接口
 * 
 * @author wangguangwu
 */
public interface Result {
    /**
     * 获取调用返回值
     * 
     * @return 调用返回值
     */
    Object getValue();
    
    /**
     * 获取调用异常
     * 
     * @return 调用异常
     */
    Throwable getException();
    
    /**
     * 判断是否有异常
     * 
     * @return 如果有异常返回true，否则返回false
     */
    boolean hasException();
}
