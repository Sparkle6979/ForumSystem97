package com.example.forumsystem.utils;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/4/21 11:55
 */
public interface ForumConstant {
    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;
    /**
     * 重复激活
     */
    int ACTIVATION_REAPEAT = 1;
    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;
    /**
     * 12个小时
     */
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;
    /**
     * 100天
     */
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;
}
