package com.ce.majiang.exception;

public enum CustomizeErrorCode implements ICutomizeErrorCode {
    /**
     *
     */
    QUESTION_NOT_FOUND("你找的问题可能被路过的汪星人叼走了~~~~~建议追..."),
    UPDATE_USER_ERROR("更新用户信息出错！"),
    UPDATE_VIEW_ERROR("更新阅读数出错！"),
    ;

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
