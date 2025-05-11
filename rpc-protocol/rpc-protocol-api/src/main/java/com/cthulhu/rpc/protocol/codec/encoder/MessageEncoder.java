package com.cthulhu.rpc.protocol.codec.encoder;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.extension.SPI;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 消息编码器接口 - 负责将对象序列化到输出流
 *
 * @author wangguangwu
 */
@SPI
public interface MessageEncoder {
    /**
     * 编码 - 将对象序列化到输出流
     *
     * @param url 服务URL
     * @param output 输出流
     * @param message 待序列化的对象
     * @throws IOException 编码过程中的IO异常
     */
    void encode(URL url, OutputStream output, Object message) throws IOException;
}
