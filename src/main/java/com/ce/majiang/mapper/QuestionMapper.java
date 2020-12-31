package com.ce.majiang.mapper;

/**
 * @author c__e
 * @date 2020/12/25 11:25
 */

import com.ce.majiang.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    @Insert("insert into question(title,description,tag,creator,gmt_created,gmt_modified)" +
            " values(#{title},#{description},#{tag},#{creator},#{gmtCreated},#{gmtModified})")
    void insertQuestion(Question question);

    @Select("select * from question")
    List<Question> list();

    @Select("select * from question limit #{offset},#{size}")
    List<Question> pageList(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();
}
