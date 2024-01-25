package org.ygcxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ygcxy.entity.KnowCount;
import org.ygcxy.entity.KnowledgeContent;
import org.ygcxy.entity.SensitiveCount;

import java.util.List;

@Mapper
public interface KnowledgeMapper extends BaseMapper<KnowledgeContent> {
    @Select("SELECT COUNT(*) AS total_count, SUM(is_sensitive = 1) AS sensitive_count FROM knowledge_content kc")
    SensitiveCount query();

    @Select("SELECT COUNT(view) FROM knowledge_content")
    Long queryView();

    @Select("SELECT DATE_FORMAT(kc_publish_time, '%Y-%m') AS month, COUNT(*) AS `count`\n" +
            "FROM knowledge_content kc\n" +
            "where  year(kc_publish_time) = #{year}\n" +
            "GROUP BY month\n" +
            "ORDER BY month;")
    List<KnowCount> queryCount(int year);


}
