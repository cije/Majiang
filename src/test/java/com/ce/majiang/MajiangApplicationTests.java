package com.ce.majiang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ce.majiang.dto.PaginationDTO;
import com.ce.majiang.mapper.QuestionMapper;
import com.ce.majiang.mapper.UserMapper;
import com.ce.majiang.model.Question;
import com.ce.majiang.model.User;
import com.ce.majiang.service.QuestionService;
import com.ce.majiang.service.UserService;
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
        PaginationDTO list = questionService.list(1, 5, null, null);
        System.out.println(list);
    }

    @Test
    public void insertQuestion() {
        Question question = new Question();
        int i = 0;
        for (i = 1; i < 30; i++) {
            question.setTitle("问题标题" + i).setDescription("问题描述" + i)
                    .setTag("问题" + i % 7)
                    .setCreator(16L)
                    .setGmtCreated(System.currentTimeMillis())
                    .setGmtModified(System.currentTimeMillis());
            questionService.saveQuestion(question);
        }
        for (; i <= 35; i++) {
            question.setTitle("问题标题" + i).setDescription("问题描述" + i)
                    .setTag("问题" + i % 7)
                    .setCreator(17L)
                    .setGmtCreated(System.currentTimeMillis())
                    .setGmtModified(System.currentTimeMillis());
            questionService.saveQuestion(question);
        }
    }

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test2() {
        Integer count = questionMapper.selectCount(new QueryWrapper<Question>().eq("creator", 17));
        System.out.println(count);
    }

    @Test
    public void test3() {
        User byAccountId = userMapper.selectOne(new QueryWrapper<User>().eq("account_id", 43648247).last("limit 1"));
        System.out.println(byAccountId);
    }

    @Test
    public void test4() {
        String title = "titl";
        String tag = "tag";
        String description = "descriptio";
        Question question = new Question();
        question.setTitle(title).
                setDescription(description).
                setTag(tag);
        question.setCreator(16L).
                setGmtCreated(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreated());
        question.setId(null);
        questionService.createOrUpdateQuestion(question);
    }
}
