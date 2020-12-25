package com.ce.majiang.controller;

import com.ce.majiang.model.Question;
import com.ce.majiang.model.User;
import com.ce.majiang.service.QuestionService;
import com.ce.majiang.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author c__e
 * @date 2020/12/25 10:18
 */
@Controller
public class PublishController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String publish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request, Model model) {

        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (ObjectUtils.isEmpty(cookies)) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
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
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title).

                setDescription(description).

                setTag(tag);
        question.setCreator(user.getId()).

                setGmtCreated(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreated());
        questionService.saveQuestion(question);
        return "redirect:/";
    }
}
