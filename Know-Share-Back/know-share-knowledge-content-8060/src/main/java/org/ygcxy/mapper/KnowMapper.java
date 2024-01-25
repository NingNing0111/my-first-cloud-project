package org.ygcxy.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.ygcxy.dto.request.KnowDtoRequest;
import org.ygcxy.entity.KnowledgeContent;
import org.ygcxy.handler.type.ListToVarcharTypeHandler;

import java.util.List;

@Mapper
@Repository
public interface KnowMapper extends BaseMapper<KnowledgeContent> {

    @Select("select know.* from knowledge_content know JOIN favorites fa where know.kc_id = fa.kc_id AND fa.is_favorites_show AND fa.user_id = #{userId}")
    @Results({
            @Result(column = "is_kc_publish",property = "isKcBan"),
            @Result(column = "kc_category",property = "kcCategory",typeHandler = ListToVarcharTypeHandler.class)
    })
    Page<KnowledgeContent> queryCollectPageByUserId(Long userId, Page page);

}
