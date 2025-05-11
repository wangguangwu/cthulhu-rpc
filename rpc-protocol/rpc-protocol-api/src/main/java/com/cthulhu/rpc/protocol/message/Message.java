package com.cthulhu.rpc.protocol.message;

import java.io.Serializable;
import java.util.Map;

/**
 * 消息接口 - 定义RPC通信的基本消息结构
 *
 * @author wangguangwu
 */
public interface Message extends Serializable {
    /**
     * 获取消息ID
     *
     * @return 消息ID
     */
    long getId();

    /**
     * 设置消息ID
     *
     * @param id 消息ID
     */
    void setId(long id);

    /**
     * 获取消息类型
     *
     * @return 消息类型
     */
    byte getMessageType();

    /**
     * 获取附加属性
     *
     * @return 附加属性Map
     */
    Map<String, Object> getAttachments();

    /**
     * 获取特定附加属性
     *
     * @param key 属性键
     * @return 属性值
     */
    Object getAttachment(String key);

    /**
     * 设置附加属性
     *
     * @param key 属性键
     * @param value 属性值
     */
    void setAttachment(String key, Object value);
}
