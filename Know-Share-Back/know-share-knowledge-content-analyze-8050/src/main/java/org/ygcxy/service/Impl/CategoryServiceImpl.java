package org.ygcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.comment.StatusCode;
import org.ygcxy.entity.KnowledgeContent;
import org.ygcxy.entity.User;
import org.ygcxy.feign.UserFeign;
import org.ygcxy.mapper.KnowledgeMapper;
import org.ygcxy.service.CategoryService;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: org.ygcxy.service.Impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/24 22:37
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final KnowledgeMapper knowMapper;
    private final UserFeign userFeign;
    @Override
    public ResponseResult findCategory(Long userId) {
        User user = userFeign.findUserById(userId);
        if (user != null && user.getIsUserBan()){
            return ResponseResult.setCommonStatus(StatusCode.NOT_FOUND);
        }
        try{
            QueryWrapper<KnowledgeContent> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id",userId).eq("is_kc_show",1);
            List<KnowledgeContent> knowledgeContents = knowMapper.selectList(queryWrapper);
            HashMap<String, Double> map = new HashMap<>();
            for (KnowledgeContent know : knowledgeContents){
                for (String category : know.getKcCategory()){
                    map.put(category,map.getOrDefault(category,0.0)+1);
                }
            }
            DecimalFormat df = new DecimalFormat("#.##"); //保留小数点后两位
            double count = map.values().stream().mapToDouble(value -> value).sum();
            for(Map.Entry<String, Double> entry : map.entrySet()){
                String key = entry.getKey();
                double value = entry.getValue();
                //计算新值
                double newValue = value/count *100;

                newValue = Double.parseDouble(df.format(newValue));
                //更新
                map.put(key, newValue);
            }
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }
}
