package com.ce.majiang.service;


import com.ce.majiang.dto.PaginationDTO;
import com.ce.majiang.dto.QuestionDTO;
import com.ce.majiang.mapper.QuestionMapper;
import com.ce.majiang.mapper.UserMapper;
import com.ce.majiang.model.Question;
import com.ce.majiang.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author c__e
 * @date 2020/12/25 14:31
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        if (ObjectUtils.isEmpty(questions)) {
            return null;
        }
        return toQuestionDTOList(questions);
    }

    private List<QuestionDTO> toQuestionDTOList(List<Question> questions) {
        List<QuestionDTO> list = new ArrayList<>(questions.size());
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }
        return list;
    }

    public PaginationDTO list(Integer page, Integer size) {
        return list(null, page, size);
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        // 总记录条数
        Integer totalCount;
        if (userId != null) {
            totalCount = questionMapper.countByUserId(userId);
        } else {
            totalCount = questionMapper.count();
        }
        paginationDTO.setTotalCount(totalCount);
        // 总页数
        int totalPage = 0;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        paginationDTO.setTotalPage(totalPage);
        // 页码越界处理
        if (page > totalPage) {
            page = totalPage;
        }
        if (page < 1) {
            page = 1;
        }
        paginationDTO.setPage(page);
        // 查询数据，封装结果
        Integer offset = size * (page - 1);
        List<Question> questions;
        if (userId != null) {
            questions = questionMapper.pageListByUserId(userId, offset, size);
        } else {
            questions = questionMapper.pageList(offset, size);
        }
        List<QuestionDTO> list = toQuestionDTOList(questions);
        // 设置数据
        paginationDTO.setQuestions(list);
        // 设置 首页 上一页 页码 下一页 末页
        paginationDTO.setPagination(page, size);
        return paginationDTO;
    }

    public QuestionDTO findById(Integer id) {
        Question question = questionMapper.findById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}