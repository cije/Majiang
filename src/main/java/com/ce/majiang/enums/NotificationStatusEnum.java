package com.ce.majiang.enums;

import java.io.Serializable;

/**
 * @author c__e
 * @date 2021/1/13 15:12
 */
public enum NotificationStatusEnum implements Serializable {
    /**
     * 未读（0）
     * 已读（1）
     */
    UNREAD(0), READ(1);

    private final int status;

    NotificationStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
