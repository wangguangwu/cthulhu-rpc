package com.cthulhu.rpc.governance.ratelimit;

import com.cthulhu.rpc.core.extension.SPI;

/**
 * 限流器接口
 * 
 * @author cthulhu-rpc
 */
@SPI("default")
public interface RateLimiter {
    
    /**
     * 尝试获取一个许可
     * 
     * @return 如果获取许可成功，返回true；否则返回false
     */
    boolean tryAcquire();
    
    /**
     * 尝试获取指定数量的许可
     * 
     * @param permits 许可数量
     * @return 如果获取许可成功，返回true；否则返回false
     */
    boolean tryAcquire(int permits);
    
    /**
     * 获取当前限流器的限流速率
     * 
     * @return 限流速率（每秒请求数）
     */
    double getRate();
    
    /**
     * 设置限流速率
     * 
     * @param rate 限流速率（每秒请求数）
     */
    void setRate(double rate);
}
