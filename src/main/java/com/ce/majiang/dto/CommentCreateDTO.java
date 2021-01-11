package com.ce.majiang.dto;

import java.io.Serializable;

/**
 * @author c__e
 * @date 2021/1/7 10:33
 */
public class CommentCreateDTO implements Serializable {
    private Long parentId;
    private String content;
    private Integer type;

    public Long getParentId() {
        return parentId;
    }

    public CommentCreateDTO setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public CommentCreateDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public CommentCreateDTO setType(Integer type) {
        this.type = type;
        return this;
    }
}
