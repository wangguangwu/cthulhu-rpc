package com.cthulhu.rpc.protocol.codec;

import com.cthulhu.rpc.core.extension.SPI;
import com.cthulhu.rpc.protocol.codec.decoder.MessageDecoder;
import com.cthulhu.rpc.protocol.codec.encoder.MessageEncoder;

/**
 * 编解码器接口 - 组合了编码器和解码器的功能
 * 负责将请求和响应在网络传输中进行序列化和反序列化
 *
 * @author wangguangwu
 */
@SPI
public interface Codec extends MessageEncoder, MessageDecoder {
    // 继承了 MessageEncoder 和 MessageDecoder 的所有方法
}
