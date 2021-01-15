package com.ce.majiang.controller;

import com.ce.majiang.dto.CommentCreateDTO;
import com.ce.majiang.dto.CommentDTO;
import com.ce.majiang.enums.CommentTypeEnum;
import com.ce.majiang.model.Comment;
import com.ce.majiang.model.User;
import com.ce.majiang.result.Result;
import com.ce.majiang.result.ResultStatus;
import com.ce.majiang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author c__e
 * @date 2021/1/7 10:18
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/comment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Object> post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (ObjectUtils.isEmpty(user)) {
            return Result.failure(ResultStatus.NOT_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId())
                .setContent(commentCreateDTO.getContent())
                .setType(commentCreateDTO.getType())
                .setGmtCreated(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreated());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.save(comment, user);
        return Result.success();
    }

    @ResponseBody
    @GetMapping("/comment/{id}")
    public Result<Object> comments(@PathVariable("id") Long id) {
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return Result.success(comments);
    }
}
