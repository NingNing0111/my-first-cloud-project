package org.ygcxy.mepper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.ygcxy.entity.User;
import org.ygcxy.entity.UserCount;

import java.util.List;

@Mapper
public interface UserAnalyzeMapper extends BaseMapper<User> {
    @Select("SELECT DATE_FORMAT(user_create_time, '%Y-%m') AS month, COUNT(*) AS `count`\n" +
            "FROM user\n" +
            "where  year(user_create_time) = #{year}\n" +
            "GROUP BY month\n" +
            "ORDER BY month;")
    List<UserCount> countByYear(int year);
}
