package com.ce.majiang;

import com.ce.majiang.dto.PaginationDTO;
import com.ce.majiang.mapper.QuestionMapper;
import com.ce.majiang.model.Question;
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

    @Test
    public void insertQuestion() {
        Question question = new Question();
        int i = 0;
        for (i = 1; i < 30; i++) {
            question.setTitle("问题标题" + i).setDescription("问题描述" + i)
                    .setTag("问题" + i % 7)
                    .setCreator(16)
                    .setGmtCreated(System.currentTimeMillis())
                    .setGmtModified(System.currentTimeMillis());
            questionService.saveQuestion(question);
        }
        for (; i <= 35; i++) {
            question.setTitle("问题标题" + i).setDescription("问题描述" + i)
                    .setTag("问题" + i % 7)
                    .setCreator(17)
                    .setGmtCreated(System.currentTimeMillis())
                    .setGmtModified(System.currentTimeMillis());
            questionService.saveQuestion(question);
        }
    }

}
