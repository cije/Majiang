package com.ce.majiang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.annotations.UpdateProvider;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author c__e
 * @date 2020/12/25 11:27
 */
@TableName("question")
public class Question implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("title")
    private String title;
    @TableField("description")
    private String description;
    @TableField("gmt_created")
    private Long gmtCreated;
    @TableField("gmt_modified")
    private Long gmtModified;
    @TableField("creator")
    private Integer creator;
    @TableField("tag")
    private String tag;
    @TableField("view_count")
    private Integer viewCount;
    @TableField("comment_count")
    private Integer commentCount;
    @TableField("like_count")
    private Integer likeCount;


    public Integer getId() {
        return id;
    }

    public Question setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Question setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getGmtCreated() {
        return gmtCreated;
    }

    public Question setGmtCreated(Long gmtCreated) {
        this.gmtCreated = gmtCreated;
        return this;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public Question setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    public Integer getCreator() {
        return creator;
    }

    public Question setCreator(Integer creator) {
        this.creator = creator;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public Question setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Question setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Question setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public Question setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Question.class.getSimpleName() + "[", "]")
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
                .toString();
    }
}
