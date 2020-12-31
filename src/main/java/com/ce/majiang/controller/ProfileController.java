package com.ce.majiang.controller;

import com.ce.majiang.dto.PaginationDTO;
import com.ce.majiang.model.User;
import com.ce.majiang.service.QuestionService;
import com.ce.majiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author c__e
 * @date 2020/12/31 10:20
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    private final String QUESTIONS = "questions";
    private final String REPLIES = "replies";

    @RequestMapping("/{action}")
    public String profile(
            HttpServletRequest request,
            @PathVariable(name = "action") String action,
            Model model,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        Cookie[] cookies = request.getCookies();
        if (ObjectUtils.isEmpty(cookies)) {
            return "redirect:/";
        }
        User user = null;
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                user = userService.findByToken(token);
                if (!ObjectUtils.isEmpty(user)) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        if (ObjectUtils.isEmpty(user)) {
            return "redirect:/";
        }
        if (QUESTIONS.equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");

        } else if (REPLIES.equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
