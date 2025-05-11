package com.cthulhu.rpc.protocol.cthulhu.handler;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.exception.RpcException;
import com.cthulhu.rpc.core.invoker.Invocation;
import com.cthulhu.rpc.core.invoker.Invoker;
import com.cthulhu.rpc.protocol.cthulhu.message.CthulhuRequest;
import com.cthulhu.rpc.protocol.cthulhu.message.CthulhuResponse;
import com.cthulhu.rpc.protocol.handler.ProtocolHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cthulhu 协议处理器
 * 
 * @author wangguangwu
 */
public class CthulhuProtocolHandler implements ProtocolHandler {
    
    /**
     * 服务调用器映射表
     */
    private final Map<String, Invoker<?>> invokers = new ConcurrentHashMap<>();
    
    /**
     * 服务URL
     */
    private final URL url;
    
    /**
     * 构造函数
     * 
     * @param url 服务URL
     */
    public CthulhuProtocolHandler(URL url) {
        this.url = url;
    }
    
    /**
     * 注册服务调用器
     * 
     * @param serviceKey 服务键
     * @param invoker 服务调用器
     */
    public void registerInvoker(String serviceKey, Invoker<?> invoker) {
        invokers.put(serviceKey, invoker);
    }
    
    /**
     * 注销服务调用器
     * 
     * @param serviceKey 服务键
     */
    public void unregisterInvoker(String serviceKey) {
        invokers.remove(serviceKey);
    }
    
    /**
     * 判断处理器是否没有服务
     * 
     * @return 如果没有服务返回true，否则返回false
     */
    public boolean isEmpty() {
        return invokers.isEmpty();
    }
    
    /**
     * 处理请求
     * 
     * @param request 请求对象
     * @return 响应对象
     * @throws RpcException 处理异常
     */
    public CthulhuResponse handleRequest(CthulhuRequest request) throws RpcException {
        CthulhuResponse response = new CthulhuResponse();
        response.setId(request.getId());
        
        try {
            // 获取服务调用器
            Invoker<?> invoker = getInvoker(request);
            if (invoker == null) {
                throw new RpcException(RpcException.SERVICE_NOT_FOUND_EXCEPTION, "找不到服务: " + request.getInterfaceName());
            }
            
            // 创建调用信息
            Invocation invocation = createInvocation(request);
            
            // 调用服务
            com.cthulhu.rpc.core.invoker.Result result = invoker.invoke(invocation);
            
            // 设置响应结果
            if (result.hasException()) {
                response.setException(result.getException());
            } else {
                response.setResult(result.getValue());
            }
        } catch (Throwable e) {
            response.setException(e);
        }
        
        return response;
    }
    
    /**
     * 获取服务调用器
     * 
     * @param request 请求对象
     * @return 服务调用器
     */
    private Invoker<?> getInvoker(CthulhuRequest request) {
        String serviceKey = request.getInterfaceName();
        return invokers.get(serviceKey);
    }
    
    /**
     * 创建调用信息
     * 
     * @param request 请求对象
     * @return 调用信息
     */
    private Invocation createInvocation(CthulhuRequest request) {
        return new Invocation() {
            @Override
            public String getMethodName() {
                return request.getMethodName();
            }
            
            @Override
            public Class<?>[] getParameterTypes() {
                return request.getParameterTypes();
            }
            
            @Override
            public Object[] getArguments() {
                return request.getArguments();
            }
            
            @Override
            public Map<String, Object> getAttachments() {
                return request.getAttachments();
            }
            
            @Override
            public Object getAttachment(String key) {
                return request.getAttachment(key);
            }
            
            @Override
            public void setAttachment(String key, Object value) {
                request.setAttachment(key, value);
            }
        };
    }
}
