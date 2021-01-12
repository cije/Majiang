package com.ce.majiang.dto;

import com.ce.majiang.model.Question;
import com.ce.majiang.model.User;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author c__e
 * @date 2020/12/25 19:56
 */
public class QuestionDTO extends Question {
    private User user;
    private List<Question> relatedQuestions;

    public User getUser() {
        return user;
    }

    public QuestionDTO setUser(User user) {
        this.user = user;
        return this;
    }

    public List<Question> getRelatedQuestions() {
        return relatedQuestions;
    }

    public QuestionDTO setRelatedQuestions(List<Question> relatedQuestions) {
        this.relatedQuestions = relatedQuestions;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", QuestionDTO.class.getSimpleName() + "[", "]")
                .add("id=" + this.getId())
                .add("title='" + this.getTitle() + "'")
                .add("description='" + this.getDescription() + "'")
                .add("gmtCreated=" + this.getGmtCreated())
                .add("gmtModified=" + this.getGmtModified())
                .add("creator=" + this.getCreator())
                .add("tag='" + this.getTag() + "'")
                .add("viewCount=" + this.getViewCount())
                .add("commentCount=" + this.getCommentCount())
                .add("likeCount=" + this.getLikeCount())
                .add("user=" + user)
                .toString();
    }
}
