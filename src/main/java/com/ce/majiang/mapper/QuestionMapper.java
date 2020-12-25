package com.ce.majiang.mapper;

/**
 * @author c__e
 * @date 2020/12/25 11:25
 */

import com.ce.majiang.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionMapper {

    @Insert("insert into question(title,description,tag,creator,gmt_created,gmt_modified)" +
            " values(#{title},#{description},#{tag},#{creator},#{gmtCreated},#{gmtModified})")
    void insertQuestion(Question question);
}
