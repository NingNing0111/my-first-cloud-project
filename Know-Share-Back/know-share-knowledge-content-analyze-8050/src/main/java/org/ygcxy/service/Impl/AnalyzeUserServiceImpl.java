package org.ygcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.comment.StatusCode;
import org.ygcxy.entity.Favorites;
import org.ygcxy.entity.KnowledgeContent;
import org.ygcxy.entity.Likes;
import org.ygcxy.entity.User;
import org.ygcxy.feign.UserFeign;
import org.ygcxy.mapper.FavoritesMapper;
import org.ygcxy.mapper.KnowledgeMapper;
import org.ygcxy.mapper.LikesMapper;
import org.ygcxy.service.AnalyzeUserService;

import java.util.HashMap;
import java.util.List;

/**
 * @Project: org.ygcxy.service.Impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/24 22:48
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyzeUserServiceImpl implements AnalyzeUserService {

    private final UserFeign userFeign;
    private final KnowledgeMapper knowMapper;
    private final LikesMapper likesMapper;
    private final FavoritesMapper favoritesMapper;







    @Override
    public ResponseResult analyzeUserCount(Long userId) {
        try{
            User user = userFeign.findUserById(userId);
            if (user == null || user.getIsUserBan()){
                return ResponseResult.setCommonStatus(StatusCode.NOT_FOUND);
            }
            HashMap<String, Long> mp = new HashMap<>();

            QueryWrapper<KnowledgeContent> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId).eq("is_kc_show",1);
            List<KnowledgeContent> knowledgeContents = knowMapper.selectList(queryWrapper);
            Long knowCount = knowMapper.selectCount(queryWrapper);
            mp.put("knowCount", knowCount);
            mp.put("likeCount",likes(knowledgeContents));
            mp.put("viewCount", views(knowledgeContents));
            mp.put("collectCount", collect(knowledgeContents));
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,mp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 点赞量
     * @param knowledgeContents
     */
    public Long likes(List<KnowledgeContent> knowledgeContents){
        Long count = 0L;
        for (KnowledgeContent know : knowledgeContents){
            QueryWrapper<Likes> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("kc_id",know.getKcId()).eq("is_like_show",1);
            count = count + likesMapper.selectCount(queryWrapper);
        }
        return count;
    }

    /**
     * 浏览量
     */
    public Long views(List<KnowledgeContent> knowledgeContents){
        Long count = 0L;
        for (KnowledgeContent know : knowledgeContents){
            count = count + know.getView();
        }
        return count;
    }

    /**
     * 收藏量
     */
    public Long collect(List<KnowledgeContent> knowledgeContents){
        Long count = 0L;
        for (KnowledgeContent know: knowledgeContents) {
            QueryWrapper<Favorites> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("kc_id",know.getKcId()).eq("is_favorites_show",1);
            count = count + favoritesMapper.selectCount(queryWrapper);
        }
        return count;
    }
}
