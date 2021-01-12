package com.ce.majiang.controller;

import com.ce.majiang.dto.CommentDTO;
import com.ce.majiang.dto.QuestionDTO;
import com.ce.majiang.enums.CommentTypeEnum;
import com.ce.majiang.service.CommentService;
import com.ce.majiang.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author c__e
 * @date 2021/1/3 16:29
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id, Model model) {
        QuestionDTO questionDTO = questionService.findById(id);
        System.out.println(questionDTO);
        // 增加阅读数
        questionService.incView(id);
        // 获取评论
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        return "question";
    }
}
