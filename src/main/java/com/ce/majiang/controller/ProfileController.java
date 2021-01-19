package com.ce.majiang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ce.majiang.dto.NotificationDTO;
import com.ce.majiang.dto.PaginationDTO;
import com.ce.majiang.dto.QuestionDTO;
import com.ce.majiang.enums.NotificationStatusEnum;
import com.ce.majiang.model.Notification;
import com.ce.majiang.model.User;
import com.ce.majiang.service.NotificationService;
import com.ce.majiang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author c__e
 * @date 2020/12/31 10:20
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/{action}")
    public String profile(
            HttpServletRequest request,
            @PathVariable(name = "action") String action,
            Model model,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        if (ObjectUtils.isEmpty(user)) {
            return "redirect:/";
        }
        String questions = "questions";
        String replies = "replies";
        if (questions.equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PaginationDTO<QuestionDTO> paginationDTO = questionService.list(page, size, null, user.getId());
            model.addAttribute("pagination", paginationDTO);

        } else if (replies.equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            PaginationDTO<NotificationDTO> paginationDTO = notificationService.list(user.getId(), page, size);
            model.addAttribute("pagination", paginationDTO);
        }
        return "profile";
    }
}
