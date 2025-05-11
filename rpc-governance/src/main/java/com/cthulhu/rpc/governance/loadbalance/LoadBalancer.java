package com.cthulhu.rpc.governance.loadbalance;

import com.cthulhu.rpc.core.extension.SPI;

import java.util.List;

/**
 * 负载均衡器接口
 * 
 * @author cthulhu-rpc
 */
@SPI("random")
public interface LoadBalancer {
    
    /**
     * 从可用服务列表中选择一个服务
     * 
     * @param serviceList 可用服务列表
     * @param <T> 服务类型
     * @return 选择的服务
     */
    <T> T select(List<T> serviceList);
}
