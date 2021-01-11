package com.ce.majiang.enums;

import com.ce.majiang.model.Comment;

import java.io.Serializable;

/**
 * 评论类别枚举
 *
 * @author c__e
 * @date 2021/1/7 15:03
 */
public enum CommentTypeEnum implements Serializable {
    /**
     * 问题
     */
    QUESTION(1),
    /**
     * 评论
     */
    COMMENT(2),
    ;


    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (type.equals(commentTypeEnum.getType())) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }
}
