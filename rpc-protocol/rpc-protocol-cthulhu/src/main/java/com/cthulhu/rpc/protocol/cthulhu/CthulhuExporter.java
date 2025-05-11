package com.cthulhu.rpc.protocol.cthulhu;

import com.cthulhu.rpc.core.exporter.AbstractExporter;
import com.cthulhu.rpc.core.invoker.Invoker;

/**
 * Cthulhu 协议导出器
 * 
 * @author wangguangwu
 * @param <T> 服务类型
 */
public class CthulhuExporter<T> extends AbstractExporter<T> {
    
    /**
     * 服务键
     */
    private final String key;
    
    /**
     * 构造函数
     * 
     * @param invoker 服务调用器
     * @param key 服务键
     */
    public CthulhuExporter(Invoker<T> invoker, String key) {
        super(invoker);
        this.key = key;
    }
    
    /**
     * 获取服务键
     * 
     * @return 服务键
     */
    public String getKey() {
        return key;
    }
    
    @Override
    public void unexport() {
        super.unexport();
        // 从 CthulhuProtocol 中注销服务
        String addr = getInvoker().getUrl().getParameter("host") + ":" + getInvoker().getUrl().getParameter("port");
        String key = getKey();
        
        // 获取 CthulhuProtocol 单例实例并注销服务
        CthulhuProtocol protocol = CthulhuProtocol.getInstance();
        protocol.unregisterInvoker(addr, key);
    }
}
