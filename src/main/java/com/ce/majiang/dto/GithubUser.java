package com.ce.majiang.dto;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author c__e
 * @date 2020/12/23 17:11
 */
public class GithubUser implements Serializable {
    private Long id;
    private String name;
    private String bio;

    @Override
    public String toString() {
        return new StringJoiner(", ", GithubUser.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("bio='" + bio + "'")
                .toString();
    }

    public Long getId() {
        return id;
    }

    public GithubUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GithubUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public GithubUser setBio(String bio) {
        this.bio = bio;
        return this;
    }
}
