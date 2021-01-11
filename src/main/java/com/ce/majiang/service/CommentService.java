package com.ce.majiang.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ce.majiang.dto.CommentDTO;
import com.ce.majiang.enums.CommentTypeEnum;
import com.ce.majiang.mapper.CommentMapper;
import com.ce.majiang.mapper.QuestionMapper;
import com.ce.majiang.mapper.UserMapper;
import com.ce.majiang.model.Comment;
import com.ce.majiang.model.Question;
import com.ce.majiang.model.User;
import com.ce.majiang.result.ResultStatus;
import com.ce.majiang.result.exception.CustomizeException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author c__e
 * @date 2021/1/7 10:12
 */
@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(Comment comment) {
        if (ObjectUtils.isEmpty(comment.getParentId()) || comment.getParentId() == 0) {
            throw new CustomizeException(ResultStatus.COMMENT_PARAM_NOT_FOUND);
        }
        if (ObjectUtils.isEmpty(comment.getType()) || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(ResultStatus.COMMENT_TYPE_PARAM_WRONG);
        }
        int inserted;
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            // 回复评论
            Comment dbComment = commentMapper.selectById(comment.getParentId());
            if (ObjectUtils.isEmpty(dbComment)) {
                throw new CustomizeException(ResultStatus.COMMENT_NOT_FOUND);
            }
            inserted = commentMapper.insert(comment);
        } else {
            // 回复问题
            Question question = questionMapper.selectById(comment.getParentId());
            if (ObjectUtils.isEmpty(question)) {
                throw new CustomizeException(ResultStatus.QUESTION_NOT_FOUND);
            }
            inserted = commentMapper.insert(comment);
            questionMapper.updateCommentCountById(question);
        }
        return inserted != 0;
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum typeEnum) {
        List<Comment> comments = commentMapper.selectList(
                new QueryWrapper<Comment>()
                        .eq("parent_id", id)
                        .eq("type", typeEnum.getType())
                        .orderByDesc("gmt_created"));
        if (CollectionUtils.isEmpty(comments)) {
            return new ArrayList<>();
        }
        // 获取去重的评论人 并转换为map
        Set<Long> commentators = comments.stream().map(Comment::getCommentator).collect(Collectors.toSet());
        List<User> users = userMapper.selectBatchIds(commentators);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user));
        // comment转commentDTO
        return comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
    }
}
