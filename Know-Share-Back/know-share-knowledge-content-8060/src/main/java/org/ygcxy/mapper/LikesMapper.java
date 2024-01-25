package org.ygcxy.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.ygcxy.entity.Likes;

@Mapper
@Repository
public interface LikesMapper extends BaseMapper<Likes> {

}
