package com.cthulhu.rpc.protocol.cthulhu.serialization;

import com.cthulhu.rpc.core.extension.ExtensionLoader;
import com.cthulhu.rpc.protocol.constants.ProtocolConstants;
import com.cthulhu.rpc.protocol.serialization.Serialization;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 序列化工厂类 - 负责创建和缓存序列化实例
 *
 * @author wangguangwu
 */
public class SerializationFactory {

    /**
     * 序列化实例缓存
     */
    private static final ConcurrentMap<Byte, Serialization> SERIALIZATION_MAP = new ConcurrentHashMap<>();

    /**
     * 获取序列化实例
     *
     * @param type 序列化类型
     * @return 序列化实例
     */
    public static Serialization getSerialization(byte type) {
        Serialization serialization = SERIALIZATION_MAP.get(type);
        if (serialization == null) {
            // 根据类型创建序列化实例
            if (type == ProtocolConstants.SerializationType.JAVA) {
                serialization = new JavaSerialization();
            } else {
                // 默认使用Java序列化
                serialization = new JavaSerialization();
            }
            SERIALIZATION_MAP.putIfAbsent(type, serialization);
        }
        return serialization;
    }

    /**
     * 获取序列化实例
     *
     * @param name 序列化名称
     * @return 序列化实例
     */
    public static Serialization getSerialization(String name) {
        // 通过SPI机制获取序列化实例
        return ExtensionLoader.getExtensionLoader(Serialization.class).getExtension(name);
    }
}
