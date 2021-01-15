package com.ce.majiang.dto;

/**
 * @author c__e
 * @date 2021/1/15 11:44
 */
public class NotificationDTO {

    private Long id;
    private Long gmtCreated;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerId;
    private String typeName;
    private Integer type;

    public Long getOuterId() {
        return outerId;
    }

    public NotificationDTO setOuterId(Long outerId) {
        this.outerId = outerId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public NotificationDTO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Long getNotifier() {
        return notifier;
    }

    public NotificationDTO setNotifier(Long notifier) {
        this.notifier = notifier;
        return this;
    }

    public String getNotifierName() {
        return notifierName;
    }

    public NotificationDTO setNotifierName(String notifierName) {
        this.notifierName = notifierName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NotificationDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getGmtCreated() {
        return gmtCreated;
    }

    public NotificationDTO setGmtCreated(Long gmtCreated) {
        this.gmtCreated = gmtCreated;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public NotificationDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }


    public String getOuterTitle() {
        return outerTitle;
    }

    public NotificationDTO setOuterTitle(String outerTitle) {
        this.outerTitle = outerTitle;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public NotificationDTO setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }
}
