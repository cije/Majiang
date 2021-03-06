package com.ce.majiang.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ce.majiang.dto.PaginationDTO;
import com.ce.majiang.dto.QuestionDTO;
import com.ce.majiang.mapper.QuestionMapper;
import com.ce.majiang.mapper.UserMapper;
import com.ce.majiang.model.Question;
import com.ce.majiang.model.User;
import com.ce.majiang.result.ResultStatus;
import com.ce.majiang.result.exception.CustomizeException;
import org.apache.commons.lang3.StringUtils;
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
        int inserted = questionMapper.insert(question);
        if (inserted == 0) {
            throw new CustomizeException(ResultStatus.ADD_QUESTION_ERROR);
        }
    }

    private List<QuestionDTO> toQuestionDTOList(List<Question> questions) {
        List<QuestionDTO> list = new ArrayList<>(questions.size());
        for (Question question : questions) {
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", question.getCreator()));
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }
        return list;
    }

    public PaginationDTO<QuestionDTO> list(Integer page, Integer size) {
        return list(page, size, null, null);
    }

    public PaginationDTO<QuestionDTO> list(Integer page, Integer size, Long userId) {
        return list(page, size, null, userId);
    }

    public PaginationDTO<QuestionDTO> list(Integer page, Integer size, String search) {
        return list(page, size, search, null);
    }

    public PaginationDTO<QuestionDTO> list(Integer page, Integer size, String search, Long userId) {
        // 总记录条数
        Integer totalCount;
        if (StringUtils.isNotBlank(search)) {
            totalCount = questionMapper.selectCountBySearch(search);
        } else {
            if (!ObjectUtils.isEmpty(userId)) {
                totalCount = questionMapper.selectCount(new QueryWrapper<Question>().eq("creator", userId));
            } else {
                totalCount = questionMapper.selectCount(null);
            }
        }
        PaginationDTO<QuestionDTO> paginationDTO = setPage(totalCount, page, size);
        page = paginationDTO.getPage();
        // 查询数据，封装结果
        Integer offset = size * (page - 1);
        List<Question> questions;
        if (StringUtils.isNotBlank(search)) {
            questions = questionMapper.pageSearchList(search, offset, size);
        } else {
            if (!ObjectUtils.isEmpty(userId)) {
                questions = questionMapper.pageListByUserId(userId, offset, size);
            } else {
                questions = questionMapper.pageList(offset, size);
            }
        }
        List<QuestionDTO> list = toQuestionDTOList(questions);
        // 设置数据
        paginationDTO.setData(list);
        // 设置 首页 上一页 页码 下一页 末页
        paginationDTO.setPagination(page, size);
        return paginationDTO;
    }

    private PaginationDTO<QuestionDTO> setPage(Integer totalCount, Integer page, Integer size) {
        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        paginationDTO.setTotalCount(totalCount);
        // 总页数
        int totalPage;
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
        return paginationDTO;
    }

    public Question getById(Integer id) {
        return questionMapper.selectOne(new QueryWrapper<Question>().eq("id", id));
    }

    public QuestionDTO findById(Long id) {
        Question question = questionMapper.selectOne(new QueryWrapper<Question>().eq("id", id));
        if (ObjectUtils.isEmpty(question)) {
            throw new CustomizeException(ResultStatus.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        // 问题发起人
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", question.getCreator()));
        questionDTO.setUser(user);
        // 相关问题
        if (StringUtils.isBlank(question.getTag())) {
            questionDTO.setRelatedQuestions(new ArrayList<>());
        } else {
            List<Question> relatedQuestions = questionMapper.selectRelatedByTag(question.getId(), question.getTag());
            questionDTO.setRelatedQuestions(relatedQuestions);
        }
        return questionDTO;
    }

    /**
     * 创建或更新
     */
    @Transactional(rollbackFor = Exception.class)
    public void createOrUpdateQuestion(Question question) {
        if (ObjectUtils.isEmpty(question.getId())) {
            questionMapper.insert(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            Integer updated = questionMapper.updateQuestion(question);
            if (updated.equals(0)) {
                throw new CustomizeException(ResultStatus.QUESTION_NOT_FOUND);
            }
        }
    }

    /**
     * 增加阅读数
     *
     * @param id question Id
     */
    @Transactional(rollbackFor = CustomizeException.class)
    public void incView(Long id) {
        Integer updatedView = questionMapper.updateViewCountById(id);
        if (updatedView.equals(0)) {
            throw new CustomizeException(ResultStatus.UPDATE_VIEW_ERROR);
        }
    }

    /**
     * 查找前15热门问题
     */
    public List<Question> findTop15HotQuestion() {
        return questionMapper.selectAllOrderByViewCountDesc();
    }
}
