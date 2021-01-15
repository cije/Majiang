package com.ce.majiang.result;

import com.ce.majiang.result.exception.ICutomizeErrorCode;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 业务异常信息的描述
 */
public enum ResultStatus implements ICutomizeErrorCode, Serializable {
    /**
     *
     */
    SUCCESS(200, "请求成功"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "Bad Request"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Internal Server Error"),

    SYS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5001, "服务器被狗叼走了，小哥正在努力追赶中，请等会再回来...."),

    NOT_LOGIN(HttpStatus.UNAUTHORIZED, 4010, "未登录，请先登录"),
    UPDATE_USER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 4011, "更新用户信息出错！"),

    QUESTION_NOT_FOUND(HttpStatus.BAD_REQUEST, 4020, "你找的问题可能被路过的汪星人叼走了~~~~~建议追..."),
    ADD_QUESTION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 4021, "添加问题出错！"),
    UPDATE_VIEW_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 4022, "更新阅读数出错！"),
    COMMENT_PARAM_NOT_FOUND(HttpStatus.BAD_REQUEST, 4023, "未选中任何问题或评论进行回复"),
    COMMENT_TYPE_PARAM_WRONG(HttpStatus.BAD_REQUEST, 4024, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, 4025, "回复的评论不存在"),

    NOTIFICATION_NOT_FOUND(HttpStatus.BAD_REQUEST, 4030, "通知不存在"),
    READ_NOTIFICATION_FAIL(HttpStatus.BAD_REQUEST, 4031, "你这是在读取别人的信息呢！"),
    ;

    /**
     * 返回的HTTP状态码,  符合http请求
     */
    private HttpStatus httpStatus;
    /**
     * 业务异常码
     */
    private Integer code;
    /**
     * 业务异常信息描述
     */
    private String message;

    ResultStatus(HttpStatus httpStatus, Integer code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    ResultStatus(Integer code, String message) {
        this.httpStatus = HttpStatus.OK;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ResultStatus setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public ResultStatus setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ResultStatus setMessage(String message) {
        this.message = message;
        return this;
    }
}
