package com.ce.majiang.controller;

import com.ce.majiang.dto.PaginationDTO;
import com.ce.majiang.dto.QuestionDTO;
import com.ce.majiang.service.QuestionService;
import com.ce.majiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author c__e
 * @date 2020/12/23 11:01
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;


    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                        @RequestParam(name = "size", defaultValue = "5", required = false) Integer size,
                        @RequestParam(name = "search", required = false) String search) {
        PaginationDTO<QuestionDTO> pagination = questionService.list(page, size, search);
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        return "index";
    }
}
