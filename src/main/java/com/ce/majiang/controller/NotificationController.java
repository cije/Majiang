package com.ce.majiang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ce.majiang.dto.NotificationDTO;
import com.ce.majiang.enums.NotificationStatusEnum;
import com.ce.majiang.enums.NotificationTypeEnum;
import com.ce.majiang.model.Notification;
import com.ce.majiang.model.User;
import com.ce.majiang.result.Result;
import com.ce.majiang.result.ResultStatus;
import com.ce.majiang.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author c__e
 * @date 2021/1/15 16:39
 */
@Controller
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (ObjectUtils.isEmpty(user)) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (Arrays.stream(NotificationTypeEnum.values()).anyMatch(nt -> nt.getType() == notificationDTO.getType())) {
            return "redirect:/question/" + notificationDTO.getOuterId();
        } else {
            return "redirect:/";
        }
    }
}
