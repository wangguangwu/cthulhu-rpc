package com.cthulhu.rpc.governance.circuitbreaker;

import com.cthulhu.rpc.core.extension.SPI;

/**
 * 熔断器接口
 * 
 * @author cthulhu-rpc
 */
@SPI("default")
public interface CircuitBreaker {
    
    /**
     * 判断熔断器是否允许请求通过
     * 
     * @return 如果允许请求通过，返回true；否则返回false
     */
    boolean allowRequest();
    
    /**
     * 记录请求成功
     */
    void recordSuccess();
    
    /**
     * 记录请求失败
     */
    void recordFailure();
    
    /**
     * 获取熔断器当前状态
     * 
     * @return 熔断器状态
     */
    State getState();
    
    /**
     * 熔断器状态枚举
     */
    enum State {
        /**
         * 关闭状态，允许请求通过
         */
        CLOSED,
        
        /**
         * 开启状态，不允许请求通过
         */
        OPEN,
        
        /**
         * 半开状态，允许部分请求通过
         */
        HALF_OPEN
    }
}
