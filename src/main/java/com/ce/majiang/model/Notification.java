package com.ce.majiang.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author c__e
 * @date 2021/1/13 14:42
 */
@TableName("notification")
public class Notification implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 发起通知的用户的id
     */
    @TableField("notifier")
    private Long notifier;
    /**
     * 接收该条通知的用户id
     */
    @TableField("receiver")
    private Long receiver;
    /**
     * 回复的 问题ID或评论ID
     */
    @TableField("outerId")
    private Long outerId;
    /**
     * 通知类型
     * 回复问题/回复评论
     */
    @TableField("type")
    private Integer type;
    /**
     * 创建时间
     */
    @TableField("gmt_created")
    private Long gmtCreated;
    /**
     * 已读未读状态
     */
    @TableField("status")
    private Integer status;
    /**
     * 回复的人 名
     */
    @TableField("notifier_name")
    private String notifierName;
    /**
     * 回复的问题或评论 标题
     */
    @TableField("outer_title")
    private String outerTitle;

    public String getNotifierName() {
        return notifierName;
    }

    public Notification setNotifierName(String notifierName) {
        this.notifierName = notifierName;
        return this;
    }

    public String getOuterTitle() {
        return outerTitle;
    }

    public Notification setOuterTitle(String outerTitle) {
        this.outerTitle = outerTitle;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Notification setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getReceiver() {
        return receiver;
    }

    public Notification setReceiver(Long receiver) {
        this.receiver = receiver;
        return this;
    }

    public Long getNotifier() {
        return notifier;
    }

    public Notification setNotifier(Long notifier) {
        this.notifier = notifier;
        return this;
    }

    public Long getOuterId() {
        return outerId;
    }

    public Notification setOuterId(Long outerId) {
        this.outerId = outerId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Notification setType(Integer type) {
        this.type = type;
        return this;
    }

    public Long getGmtCreated() {
        return gmtCreated;
    }

    public Notification setGmtCreated(Long gmtCreated) {
        this.gmtCreated = gmtCreated;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Notification setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Notification.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("notifier=" + notifier)
                .add("receiver=" + receiver)
                .add("outerId=" + outerId)
                .add("type=" + type)
                .add("gmtCreated=" + gmtCreated)
                .add("status=" + status)
                .toString();
    }
}
