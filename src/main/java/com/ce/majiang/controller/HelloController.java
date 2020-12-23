package com.ce.majiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author c__e
 * @date 2020/12/23 11:01
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "c__e") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
