package com.cthulhu.rpc.protocol.cthulhu.transport;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.exception.RpcException;
import com.cthulhu.rpc.protocol.cthulhu.codec.CthulhuCodec;
import com.cthulhu.rpc.protocol.cthulhu.message.CthulhuRequest;
import com.cthulhu.rpc.protocol.cthulhu.message.CthulhuResponse;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Cthulhu 协议客户端
 * 
 * @author wangguangwu
 */
public class CthulhuClient {
    
    /**
     * 服务URL
     */
    private final URL url;
    
    /**
     * 编解码器
     */
    private final CthulhuCodec codec = new CthulhuCodec();
    
    /**
     * 客户端套接字
     */
    private Socket socket;
    
    /**
     * 是否已连接
     */
    private final AtomicBoolean connected = new AtomicBoolean(false);
    
    /**
     * 请求锁
     */
    private final Lock requestLock = new ReentrantLock();
    
    /**
     * 响应映射表
     */
    private final ConcurrentHashMap<Long, CthulhuResponse> responses = new ConcurrentHashMap<>();
    
    /**
     * 构造函数
     * 
     * @param url 服务URL
     */
    public CthulhuClient(URL url) {
        this.url = url;
    }
    
    /**
     * 连接到服务器
     * 
     * @throws RpcException 连接异常
     */
    public void connect() throws RpcException {
        if (connected.compareAndSet(false, true)) {
            try {
                // 创建客户端套接字
                String host = url.getHost();
                int port = url.getPort();
                socket = new Socket(host, port);
                
                System.out.println("已连接到 Cthulhu 服务器: " + host + ":" + port);
            } catch (IOException e) {
                connected.set(false);
                throw new RpcException(RpcException.NETWORK_EXCEPTION, "连接服务器失败: " + e.getMessage(), e);
            }
        }
    }
    
    /**
     * 发送请求
     * 
     * @param request 请求对象
     * @return 响应对象
     * @throws RpcException 发送异常
     */
    public CthulhuResponse send(CthulhuRequest request) throws RpcException {
        if (!connected.get()) {
            throw new RpcException(RpcException.NETWORK_EXCEPTION, "客户端未连接");
        }
        
        try {
            requestLock.lock();
            try {
                // 发送请求
                codec.encode(url, socket.getOutputStream(), request);
                
                // 接收响应
                CthulhuResponse response = (CthulhuResponse) codec.decode(url, socket.getInputStream());
                
                // 验证响应ID
                if (response.getId() != request.getId()) {
                    throw new RpcException(RpcException.PROTOCOL_EXCEPTION, "响应ID不匹配: " + response.getId() + " != " + request.getId());
                }
                
                return response;
            } finally {
                requestLock.unlock();
            }
        } catch (IOException e) {
            throw new RpcException(RpcException.NETWORK_EXCEPTION, "发送请求失败: " + e.getMessage(), e);
        }
    }
    
    /**
     * 关闭客户端
     */
    public void close() {
        if (connected.compareAndSet(true, false)) {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
                
                System.out.println("Cthulhu 客户端已关闭");
            } catch (IOException e) {
                throw new RpcException(RpcException.NETWORK_EXCEPTION, "关闭客户端失败: " + e.getMessage(), e);
            }
        }
    }
    
    /**
     * 判断客户端是否已连接
     * 
     * @return 如果已连接返回true，否则返回false
     */
    public boolean isConnected() {
        return connected.get() && socket != null && socket.isConnected() && !socket.isClosed();
    }
    
    /**
     * 重新连接
     * 
     * @return 如果重连成功返回true，否则返回false
     */
    public boolean reconnect() {
        close();
        try {
            connect();
            return true;
        } catch (RpcException e) {
            return false;
        }
    }
    
    /**
     * 获取远程地址
     * 
     * @return 远程地址
     */
    public InetSocketAddress getRemoteAddress() {
        if (socket != null) {
            return new InetSocketAddress(socket.getInetAddress(), socket.getPort());
        }
        return null;
    }
    
    /**
     * 获取本地地址
     * 
     * @return 本地地址
     */
    public InetSocketAddress getLocalAddress() {
        if (socket != null) {
            return new InetSocketAddress(socket.getLocalAddress(), socket.getLocalPort());
        }
        return null;
    }
    
    /**
     * 获取服务URL
     * 
     * @return 服务URL
     */
    public URL getUrl() {
        return url;
    }
}
