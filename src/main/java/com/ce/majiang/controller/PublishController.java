package com.ce.majiang.controller;

import com.ce.majiang.model.Question;
import com.ce.majiang.model.User;
import com.ce.majiang.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author c__e
 * @date 2020/12/25 10:18
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    /**
     * 发布或更新
     */
    @PostMapping("/publish")
    public String publish(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "id", required = false) Integer id,
            HttpServletRequest request, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("tag", tag);
        model.addAttribute("description", description);
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
        User user = (User) request.getSession().getAttribute("user");
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
        question.setId(id);
        questionService.createOrUpdateQuestion(question);
        return "redirect:/";
    }

    /**
     * 编辑
     */
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Question question = questionService.getById(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        model.addAttribute("description", question.getDescription());
        return "publish";
    }
}
