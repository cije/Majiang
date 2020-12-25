package com.ce.majiang.service;


import com.ce.majiang.mapper.QuestionMapper;
import com.ce.majiang.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author c__e
 * @date 2020/12/25 14:31
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }
}
