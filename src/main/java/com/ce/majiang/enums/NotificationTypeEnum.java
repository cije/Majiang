package com.ce.majiang.enums;

import java.io.Serializable;

/**
 * @author c__e
 * @date 2021/1/13 14:46
 */
public enum NotificationTypeEnum implements Serializable {
    /**
     * 回复问题
     */
    REPLY_QUESTION(1, "回复了问题"),
    /**
     * 回复评论
     */
    REPLY_COMMENT(2, "回复了评论");

    private final int type;
    private final String name;

    NotificationTypeEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String nameOfType(int type) {
        for (NotificationTypeEnum value : NotificationTypeEnum.values()) {
            if (type == value.getType()) {
                return value.getName();
            }
        }
        return "";
    }
}
