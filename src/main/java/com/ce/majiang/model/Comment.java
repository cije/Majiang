package com.ce.majiang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 评论实体类
 *
 * @author c__e
 * @date 2021/1/7 9:57
 */
@TableName("comment")
public class Comment implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("parent_id")
    private Long parentId;
    @TableField("commentator")
    private Long commentator;
    @TableField("type")
    private Integer type;
    @TableField("gmt_created")
    private Long gmtCreated;
    @TableField("gmt_modified")
    private Long gmtModified;
    @TableField("like_count")
    private Long likeCount;
    @TableField("comment_count")
    private Long commentCount;
    @TableField("content")
    private String content;

    public Long getCommentCount() {
        return commentCount;
    }

    public Comment setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Comment setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Comment setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Long getCommentator() {
        return commentator;
    }

    public Comment setCommentator(Long commentator) {
        this.commentator = commentator;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Comment setType(Integer type) {
        this.type = type;
        return this;
    }

    public Long getGmtCreated() {
        return gmtCreated;
    }

    public Comment setGmtCreated(Long gmtCreated) {
        this.gmtCreated = gmtCreated;
        return this;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public Comment setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public Comment setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Comment.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("parentId=" + parentId)
                .add("commentator=" + commentator)
                .add("type=" + type)
                .add("gmtCreated=" + gmtCreated)
                .add("gmtModified=" + gmtModified)
                .add("likeCount=" + likeCount)
                .add("commentCount=" + commentCount)
                .add("content='" + content + "'")
                .toString();
    }
}
