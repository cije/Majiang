package com.ce.majiang;

import com.ce.majiang.dto.PaginationDTO;
import com.ce.majiang.mapper.QuestionMapper;
import com.ce.majiang.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MajiangApplicationTests {

    @Test
    void contextLoads() {

    }
    @Autowired
    private QuestionService questionService;

    @Test
    void test() {
        PaginationDTO list = questionService.list(1, 5);
        System.out.println(list);
    }

}
