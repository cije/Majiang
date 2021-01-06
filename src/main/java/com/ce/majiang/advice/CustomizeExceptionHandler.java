package com.ce.majiang.advice;

import com.ce.majiang.exception.CustomizeException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author c__e
 * @date 2021/1/5 20:28
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handler(HttpServletRequest request, Throwable e) {
        ModelAndView modelAndView = new ModelAndView("error");
        if (e instanceof CustomizeException) {
            modelAndView.addObject("message", e.getMessage());
        } else {
            modelAndView.addObject("message", "服务器被狗叼走了，小哥正在努力追赶中，请等会再回来....");
        }
        return modelAndView;
    }

}
