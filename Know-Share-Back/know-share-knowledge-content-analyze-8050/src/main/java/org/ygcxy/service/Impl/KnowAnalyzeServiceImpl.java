package org.ygcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.comment.StatusCode;
import org.ygcxy.dto.request.Request;
import org.ygcxy.entity.*;
import org.ygcxy.mapper.CommentsMapper;
import org.ygcxy.mapper.FavoritesMapper;
import org.ygcxy.mapper.KnowledgeMapper;
import org.ygcxy.mapper.LikesMapper;
import org.ygcxy.service.KnowAnalyzeService;
import org.ygcxy.utils.AnalyzeUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class KnowAnalyzeServiceImpl implements KnowAnalyzeService {

    private final LikesMapper likesMapper;
    private final CommentsMapper commentsMapper;
    private final FavoritesMapper favoritesMapper;
    private final KnowledgeMapper knowledgeMapper;

    /**
     * 点赞分析
     * @param request
     * @return
     */
    @Override
    public ResponseResult analyzeLikes(Request request) {
        List<Long> list = new ArrayList<>();
        try{
            List<Date> dataRange = new ArrayList<>();
            LocalDate newDate = request.getStartTime().toLocalDate();
            while (!newDate.isAfter(request.getEndTime().toLocalDate())){
                Date date = localDateToDate(newDate);
                dataRange.add(date);
                newDate = newDate.plusDays(1);

            }
            for (Date date : dataRange){
                QueryWrapper<Likes> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("like_time",date).eq("is_like_show",1);
                Long count = likesMapper.selectCount(queryWrapper);
                list.add(count);
            }
            return ResponseResult.setCommonStatusAndData(StatusCode.COUNT_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Date localDateToDate(LocalDate newDate) {
        return Date.valueOf(newDate);
    }

    /**
     * 评论分析
     * @param request
     * @return
     */
    @Override
    public ResponseResult analyzeComments(Request request) {
        List<Long> list = new ArrayList<>();
        try{
            List<Date> dataRange = new ArrayList<>();
            LocalDate newDate = request.getStartTime().toLocalDate();
            while (!newDate.isAfter(request.getEndTime().toLocalDate())){
                Date date = localDateToDate(newDate);
                dataRange.add(date);
                newDate = newDate.plusDays(1);

            }
            for (Date date : dataRange){
                QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("comments_time",date);
                Long count = commentsMapper.selectCount(queryWrapper);
                list.add(count);
            }
            return  ResponseResult.setCommonStatusAndData(StatusCode.COUNT_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 收藏统计
     * @param request
     * @return
     */
    @Override
    public ResponseResult analyzeFavorites(Request request) {
        List<Long> list = new ArrayList<>();
        try{
            List<Date> dataRange = new ArrayList<>();
            LocalDate newDate = request.getStartTime().toLocalDate();
            while (!newDate.isAfter(request.getEndTime().toLocalDate())){
                Date date = localDateToDate(newDate);
                dataRange.add(date);
                newDate = newDate.plusDays(1);

            }
            for (Date date : dataRange){
                QueryWrapper<Favorites> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("favorite_time",date).eq("is_favorites_show",1);
                Long count = favoritesMapper.selectCount(queryWrapper);
                list.add(count);
            }
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文章总数展示
     * @param request
     * @return
     */
    @Override
    public ResponseResult analyzeKnow(Request request) {
        List<Long> list = new ArrayList<>();
        try{
            List<Date> dateRange = new ArrayList<>();
            LocalDate newDate = request.getStartTime().toLocalDate();
            while (!newDate.isAfter(request.getEndTime().toLocalDate())){
                Date date = localDateToDate(newDate);
                dateRange.add(date);
                newDate = newDate.plusDays(1);
            }
            for (Date date : dateRange){
                QueryWrapper<KnowledgeContent> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("kc_publish_time",date).eq("is_kc_show",1);
                Long count = knowledgeMapper.selectCount(queryWrapper);
                list.add(count);
            }
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 显示违禁文章总数
     * @param request
     * @return
     */
    @Override
    public ResponseResult analyzeBanKnow(Request request) {
        List<Long> list = new ArrayList<>();
        try{
            List<Date> dateRange = new ArrayList<>();
            LocalDate newDate = request.getStartTime().toLocalDate();
            while (!newDate.isAfter(request.getEndTime().toLocalDate())){
                Date date = localDateToDate(newDate);
                dateRange.add(date);
                newDate = newDate.plusDays(1);
            }
            for (Date date : dateRange){
                QueryWrapper<KnowledgeContent> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("kc_publish_time",date).eq("is_kc_show",0);
                Long count = knowledgeMapper.selectCount(queryWrapper);
                list.add(count);
            }
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseResult analyzeIsSensitivePercentage() {

        SensitiveCount query = knowledgeMapper.query();
        return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,(double)query.getSensitiveCount() / query.getTotalCount());
    }

    @Override
    public ResponseResult analyzeSumView() {
        Long views = knowledgeMapper.queryView();
        HashMap<String, Long> mp = new HashMap<>();
        mp.put("views", views);
        return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,mp);
    }

    @Override
    public ResponseResult countKnow() {
        try {
            Long count = knowledgeMapper.selectCount(null);
            HashMap<String, Long> data = new HashMap<>();
            data.put("count",count);
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,data);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult countByYear(int year) {
        // 生成默认值
        Map<String, Long> data = getDefaultCountList(year);
        List<KnowCount> knowCounts = knowledgeMapper.queryCount(year);
        knowCounts.forEach(e -> {
            data.put(e.getMonth(),e.getCount());
        });
        return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS, new TreeMap<>(data));
    }

    // 生成默认列表值
    private Map<String, Long> getDefaultCountList(int year) {
        List<String> months = AnalyzeUtils.velocityListByYear(year);
        Map<String, Long> data = new HashMap<>();
        months.forEach(e -> {
            data.put(e,0L);
        });
        return data;
    }
}
