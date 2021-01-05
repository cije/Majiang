package com.ce.majiang.controller;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * @author c__e
 * @date 2021/1/5 21:39
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomizeErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        ModelAndView modelAndView = new ModelAndView("error");
        if (status.is4xxClientError()) {
            modelAndView.addObject("message", "你这个请求错误了吧，你再好好看看？");
        } else if (status.is5xxServerError()) {
            modelAndView.addObject("message", "服务器被狗叼走了，小哥正在努力追赶中，请等会再回来....");
        } else {
            modelAndView.addObject("message", "服务器被狗叼走了，小哥正在努力追赶中，请等会再回来....");
        }
        return modelAndView;
    }


    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception e) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }
}
