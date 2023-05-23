package com.example.forumsystem.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/5/11 18:05
 */
public class Event {
    // topic名称
    private String topic;
    // 通知的用户id
    private int userId;

    // 事件的类型
    private int entityType;
    // 事件的Id
    private int entityId;
    // 事件所属的用户id
    private int entityUserId;
    // 其他数据
    private Map<String, Object> data = new HashMap<>();

    public String getTopic() {
        return topic;
    }

    public Event setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Event setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public Event setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public Event setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityUserId() {
        return entityUserId;
    }

    public Event setEntityUserId(int entityUserId) {
        this.entityUserId = entityUserId;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Event setData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
