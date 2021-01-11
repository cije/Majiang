package com.ce.majiang.dto;

import com.ce.majiang.model.User;

import java.util.StringJoiner;

/**
 * @author c__e
 * @date 2020/12/25 19:56
 */
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Long gmtCreated;
    private Long gmtModified;
    private Long creator;
    private String tag;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;

    public Long getId() {
        return id;
    }

    public QuestionDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public QuestionDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QuestionDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getGmtCreated() {
        return gmtCreated;
    }

    public QuestionDTO setGmtCreated(Long gmtCreated) {
        this.gmtCreated = gmtCreated;
        return this;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public QuestionDTO setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    public Long getCreator() {
        return creator;
    }

    public QuestionDTO setCreator(Long creator) {
        this.creator = creator;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public QuestionDTO setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public QuestionDTO setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public QuestionDTO setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public QuestionDTO setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public User getUser() {
        return user;
    }

    public QuestionDTO setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", QuestionDTO.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("description='" + description + "'")
                .add("gmtCreated=" + gmtCreated)
                .add("gmtModified=" + gmtModified)
                .add("creator=" + creator)
                .add("tag='" + tag + "'")
                .add("viewCount=" + viewCount)
                .add("commentCount=" + commentCount)
                .add("likeCount=" + likeCount)
                .add("user=" + user)
                .toString();
    }
}
