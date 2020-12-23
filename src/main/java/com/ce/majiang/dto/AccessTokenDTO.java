package com.ce.majiang.dto;

import java.util.StringJoiner;

/**
 * @author c__e
 * @date 2020/12/23 16:34
 */
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public AccessTokenDTO setClient_id(String client_id) {
        this.client_id = client_id;
        return this;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public AccessTokenDTO setClient_secret(String client_secret) {
        this.client_secret = client_secret;
        return this;
    }

    public String getCode() {
        return code;
    }

    public AccessTokenDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public AccessTokenDTO setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
        return this;
    }

    public String getState() {
        return state;
    }

    public AccessTokenDTO setState(String state) {
        this.state = state;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccessTokenDTO.class.getSimpleName() + "[", "]")
                .add("client_id='" + client_id + "'")
                .add("client_secret='" + client_secret + "'")
                .add("code='" + code + "'")
                .add("redirect_uri='" + redirect_uri + "'")
                .add("state='" + state + "'")
                .toString();
    }
}
