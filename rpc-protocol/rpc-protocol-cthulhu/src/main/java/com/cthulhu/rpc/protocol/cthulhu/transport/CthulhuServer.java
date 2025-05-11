package com.cthulhu.rpc.protocol.cthulhu.transport;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.exception.RpcException;
import com.cthulhu.rpc.protocol.cthulhu.codec.CthulhuCodec;
import com.cthulhu.rpc.protocol.cthulhu.handler.CthulhuProtocolHandler;
import com.cthulhu.rpc.protocol.cthulhu.message.CthulhuRequest;
import com.cthulhu.rpc.protocol.cthulhu.message.CthulhuResponse;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Cthulhu 协议服务器
 * 
 * @author wangguangwu
 */
public class CthulhuServer {
    
    /**
     * 服务URL
     */
    private final URL url;
    
    /**
     * 协议处理器
     */
    private final CthulhuProtocolHandler handler;
    
    /**
     * 编解码器
     */
    private final CthulhuCodec codec = new CthulhuCodec();
    
    /**
     * 服务器套接字
     */
    private ServerSocket serverSocket;
    
    /**
     * 线程池
     */
    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    
    /**
     * 客户端连接映射表
     */
    private final ConcurrentHashMap<InetSocketAddress, Socket> clients = new ConcurrentHashMap<>();
    
    /**
     * 是否正在运行
     */
    private final AtomicBoolean running = new AtomicBoolean(false);
    
    /**
     * 构造函数
     * 
     * @param url 服务URL
     * @param handler 协议处理器
     */
    public CthulhuServer(URL url, CthulhuProtocolHandler handler) {
        this.url = url;
        this.handler = handler;
    }
    
    /**
     * 启动服务器
     * 
     * @throws RpcException 启动异常
     */
    public void start() throws RpcException {
        if (running.compareAndSet(false, true)) {
            try {
                // 创建服务器套接字
                int port = url.getPort();
                serverSocket = new ServerSocket(port);
                
                // 启动接收线程
                executor.execute(this::acceptClients);
                
                System.out.println("Cthulhu 服务器已启动，监听端口: " + port);
            } catch (IOException e) {
                running.set(false);
                throw new RpcException(RpcException.NETWORK_EXCEPTION, "启动服务器失败: " + e.getMessage(), e);
            }
        }
    }
    
    /**
     * 关闭服务器
     */
    public void close() {
        if (running.compareAndSet(true, false)) {
            try {
                // 关闭所有客户端连接
                for (Socket client : clients.values()) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        // 忽略异常
                    }
                }
                clients.clear();
                
                // 关闭服务器套接字
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
                
                // 关闭线程池
                executor.shutdown();
                
                System.out.println("Cthulhu 服务器已关闭");
            } catch (IOException e) {
                throw new RpcException(RpcException.NETWORK_EXCEPTION, "关闭服务器失败: " + e.getMessage(), e);
            }
        }
    }
    
    /**
     * 接收客户端连接
     */
    private void acceptClients() {
        while (running.get()) {
            try {
                // 接收客户端连接
                Socket client = serverSocket.accept();
                InetSocketAddress remoteAddress = new InetSocketAddress(client.getInetAddress(), client.getPort());
                clients.put(remoteAddress, client);
                
                // 启动处理线程
                executor.execute(() -> handleClient(client, remoteAddress));
            } catch (IOException e) {
                if (running.get()) {
                    System.err.println("接收客户端连接失败: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * 处理客户端连接
     * 
     * @param client 客户端套接字
     * @param remoteAddress 远程地址
     */
    private void handleClient(Socket client, InetSocketAddress remoteAddress) {
        try {
            while (running.get() && client.isConnected()) {
                // 接收请求
                CthulhuRequest request = (CthulhuRequest) codec.decode(url, client.getInputStream());
                
                // 处理请求
                CthulhuResponse response = handler.handleRequest(request);
                
                // 发送响应
                codec.encode(url, client.getOutputStream(), response);
            }
        } catch (IOException e) {
            System.err.println("处理客户端连接失败: " + e.getMessage());
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                // 忽略异常
            }
            clients.remove(remoteAddress);
        }
    }
    
    /**
     * 判断服务器是否正在运行
     * 
     * @return 如果正在运行返回true，否则返回false
     */
    public boolean isRunning() {
        return running.get();
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
