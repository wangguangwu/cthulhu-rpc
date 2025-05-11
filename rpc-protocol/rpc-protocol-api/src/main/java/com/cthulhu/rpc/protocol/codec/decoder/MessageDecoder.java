package com.cthulhu.rpc.protocol.codec.decoder;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.extension.SPI;

import java.io.IOException;
import java.io.InputStream;

/**
 * 消息解码器接口 - 负责从输入流反序列化对象
 *
 * @author wangguangwu
 */
@SPI
public interface MessageDecoder {
    /**
     * 解码 - 从输入流反序列化对象
     *
     * @param url 服务URL
     * @param input 输入流
     * @return 反序列化后的对象
     * @throws IOException 解码过程中的IO异常
     */
    Object decode(URL url, InputStream input) throws IOException;
}
