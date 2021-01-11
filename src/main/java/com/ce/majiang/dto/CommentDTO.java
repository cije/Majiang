package com.ce.majiang.dto;

import com.ce.majiang.model.Comment;
import com.ce.majiang.model.User;

import java.io.Serializable;

/**
 * @author c__e
 * @date 2021/1/8 14:14
 */
public class CommentDTO extends Comment implements Serializable {
    private User user;

    public User getUser() {
        return user;
    }

    public CommentDTO setUser(User user) {
        this.user = user;
        return this;
    }
}
