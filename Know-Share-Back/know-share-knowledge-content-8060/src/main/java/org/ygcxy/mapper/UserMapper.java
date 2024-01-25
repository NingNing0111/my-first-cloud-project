package org.ygcxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.ygcxy.dto.UserDto;
import org.ygcxy.entity.User;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
