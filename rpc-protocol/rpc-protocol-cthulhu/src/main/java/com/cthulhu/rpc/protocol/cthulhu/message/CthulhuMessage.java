package com.cthulhu.rpc.protocol.cthulhu.message;

import com.cthulhu.rpc.protocol.message.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * Cthulhu 协议消息基类
 * 
 * @author wangguangwu
 */
public abstract class CthulhuMessage implements Message {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 消息ID
     */
    private long id;
    
    /**
     * 消息类型
     */
    private byte messageType;
    
    /**
     * 附加属性
     */
    private Map<String, String> attachments = new HashMap<>();
    
    @Override
    public long getId() {
        return id;
    }
    
    @Override
    public void setId(long id) {
        this.id = id;
    }
    
    @Override
    public byte getMessageType() {
        return messageType;
    }
    
    /**
     * 设置消息类型
     * 
     * @param messageType 消息类型
     */
    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }
    
    @Override
    public Map<String, String> getAttachments() {
        return attachments;
    }
    
    /**
     * 设置附加属性
     * 
     * @param attachments 附加属性
     */
    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments != null ? attachments : new HashMap<>();
    }
    
    @Override
    public String getAttachment(String key) {
        return attachments.get(key);
    }
    
    @Override
    public void setAttachment(String key, String value) {
        if (attachments == null) {
            attachments = new HashMap<>();
        }
        attachments.put(key, value);
    }
}
