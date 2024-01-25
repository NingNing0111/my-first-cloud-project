package org.ygcxy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.entity.UserCount;
import org.ygcxy.mepper.UserAnalyzeMapper;
import org.ygcxy.service.UserAnalyzeService;
import org.ygcxy.utils.AnalyzeUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserAnalyzeServiceImpl implements UserAnalyzeService {
    private final UserAnalyzeMapper userAnalyzeMapper;



    @Override
    public ResponseResult countUser() {
        try {
            Long cnt = userAnalyzeMapper.selectCount(null);
            HashMap<String, Long> data = new HashMap<>();
            data.put("count", cnt);
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS, data);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult countByYear(int year) {
        Map<String, Long> data = getDefaultCountList(year);
        List<UserCount> userCounts = userAnalyzeMapper.countByYear(year);
        userCounts.forEach(e -> {
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
