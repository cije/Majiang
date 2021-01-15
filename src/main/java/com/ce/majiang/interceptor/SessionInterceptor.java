package com.ce.majiang.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ce.majiang.enums.NotificationStatusEnum;
import com.ce.majiang.model.Notification;
import com.ce.majiang.model.User;
import com.ce.majiang.service.NotificationService;
import com.ce.majiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author c__e
 * @date 2020/12/31 16:17
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (ObjectUtils.isEmpty(cookies)) {
            return true;
        }
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                User user = userService.findByToken(token);
                if (!ObjectUtils.isEmpty(user)) {
                    Integer unreadCount = notificationService.count(new QueryWrapper<Notification>().eq("receiver", user.getId()).eq("status", NotificationStatusEnum.UNREAD.getStatus()));
                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("unreadCount", unreadCount);
                }
                break;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
