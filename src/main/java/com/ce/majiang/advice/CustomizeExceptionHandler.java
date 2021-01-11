package com.ce.majiang.advice;

import com.alibaba.fastjson.JSON;
import com.ce.majiang.result.Result;
import com.ce.majiang.result.ResultStatus;
import com.ce.majiang.result.exception.CustomizeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author c__e
 * @date 2021/1/5 20:28
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    ModelAndView handler(HttpServletRequest request, Throwable e, HttpServletResponse response) {
        logger.debug("Exception：{}", e.getMessage());
        String contentType = request.getContentType();
        if (StringUtils.equals(contentType, MediaType.APPLICATION_JSON_VALUE)) {
            Result<Object> result;
            if (e instanceof CustomizeException) {
                result = Result.failure((CustomizeException) e);
            } else {
                result = Result.failure(ResultStatus.SYS_ERROR);
            }
            try {
                response.setContentType("application/json; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(result));
                out.close();
            } catch (IOException ignored) {
            }
            return null;
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            if (e instanceof CustomizeException) {
                modelAndView.addObject("message", e.getMessage());
            } else {
                modelAndView.addObject("message", ResultStatus.SYS_ERROR.getMessage());
            }
            return modelAndView;
        }
    }

}
