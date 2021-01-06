package com.ce.majiang.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author c__e
 * @date 2020/12/24 21:01
 */
@TableName("user")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("account_id")
    private String accountId;
    @TableField("bio")
    private String bio;
    @TableField("token")
    private String token;
    @TableField("avatar_url")
    private String avatarUrl;
    @TableField("gmt_created")
    private Long gmtCreated;
    @TableField("gmt_modified")
    private Long gmtModified;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public User setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public User setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public User setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }

    public Long getGmtCreated() {
        return gmtCreated;
    }

    public User setGmtCreated(Long gmtCreated) {
        this.gmtCreated = gmtCreated;
        return this;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public User setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("accountId='" + accountId + "'")
                .add("bio='" + bio + "'")
                .add("token='" + token + "'")
                .add("avatarUrl='" + avatarUrl + "'")
                .add("gmtCreated=" + gmtCreated)
                .add("gmtModified=" + gmtModified)
                .toString();
    }
}
