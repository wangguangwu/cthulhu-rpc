package com.cthulhu.rpc.protocol.cthulhu;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.exception.RpcException;
import com.cthulhu.rpc.core.invoker.AbstractInvoker;
import com.cthulhu.rpc.core.invoker.Invocation;
import com.cthulhu.rpc.core.invoker.Result;
import com.cthulhu.rpc.protocol.cthulhu.message.CthulhuRequest;
import com.cthulhu.rpc.protocol.cthulhu.message.CthulhuResponse;
import com.cthulhu.rpc.protocol.cthulhu.transport.CthulhuClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Cthulhu 协议调用器
 * 
 * @author wangguangwu
 * @param <T> 服务类型
 */
public class CthulhuInvoker<T> extends AbstractInvoker<T> {
    
    /**
     * 客户端
     */
    private final CthulhuClient client;
    
    /**
     * 请求ID生成器
     */
    private final AtomicLong requestIdGenerator = new AtomicLong(0);
    
    /**
     * 构造函数
     * 
     * @param type 服务类型
     * @param url 服务URL
     */
    public CthulhuInvoker(Class<T> type, URL url) {
        super(type, url);
        // 创建客户端
        client = new CthulhuClient(url);
    }
    
    @Override
    protected Result doInvoke(Invocation invocation) throws Throwable {
        try {
            // 确保客户端已连接
            if (!client.isConnected()) {
                client.connect();
            }
            
            // 创建请求
            CthulhuRequest request = createRequest(invocation);
            
            // 发送请求并获取响应
            CthulhuResponse response = client.send(request);
            
            // 处理响应
            if (response.getStatus() == CthulhuResponse.OK) {
                return createResult(response.getResult(), invocation);
            } else {
                throw new RpcException(RpcException.PROTOCOL_EXCEPTION, response.getException());
            }
        } catch (RpcException e) {
            throw e;
        } catch (Throwable e) {
            throw new RpcException(RpcException.UNKNOWN_EXCEPTION, "未知异常: " + e.getMessage(), e);
        }
    }
    
    /**
     * 创建请求
     * 
     * @param invocation 调用信息
     * @return 请求对象
     */
    private CthulhuRequest createRequest(Invocation invocation) {
        CthulhuRequest request = new CthulhuRequest();
        request.setId(generateRequestId());
        request.setVersion("1.0.0");
        request.setInterfaceName(getInterface().getName());
        request.setMethodName(invocation.getMethodName());
        request.setParameterTypes(invocation.getParameterTypes());
        request.setArguments(invocation.getArguments());
        
        // 获取附件并设置到请求中
        Map<String, Object> attachments = invocation.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
            for (Map.Entry<String, Object> entry : attachments.entrySet()) {
                request.setAttachment(entry.getKey(), 
                    entry.getValue() != null ? entry.getValue().toString() : null);
            }
        }
        
        return request;
    }
    
    /**
     * 创建结果
     * 
     * @param result 响应结果
     * @param invocation 调用信息
     * @return 结果对象
     */
    private Result createResult(Object result, Invocation invocation) {
        // 创建并返回结果对象
        return new Result() {
            @Override
            public Object getValue() {
                return result;
            }
            
            @Override
            public Throwable getException() {
                return null;
            }
            
            @Override
            public boolean hasException() {
                return false;
            }
        };
    }
    
    /**
     * 生成请求ID
     * 
     * @return 请求ID
     */
    private long generateRequestId() {
        return requestIdGenerator.incrementAndGet();
    }
    
    @Override
    public boolean isAvailable() {
        return client.isConnected();
    }
    
    @Override
    public void destroy() {
        super.destroy();
        client.close();
    }
}
