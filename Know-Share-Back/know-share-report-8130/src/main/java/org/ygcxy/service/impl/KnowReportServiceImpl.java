package org.ygcxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.comment.StatusCode;
import org.ygcxy.dto.request.Request;
import org.ygcxy.entity.KcReport;
import org.ygcxy.mapper.KnowReportMapper;
import org.ygcxy.service.KnowReportService;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class KnowReportServiceImpl implements KnowReportService {

    private final KnowReportMapper knowReportMapper;

    /**
     * 举报
     * @param request
     * @return
     */
    @Override
    public ResponseResult report(Request request) {
       try{
           KcReport rp = KcReport.builder()
                   .reporterId(request.getReporterId())
                   .reportTime(new Date(System.currentTimeMillis()))
                   .kcId(request.getKcId())
                   .content(request.getContent())
                   .build();
           knowReportMapper.insert(rp);
           return ResponseResult.setCommonStatus(StatusCode.REPORT_SUCCESS);
       }catch (Exception e){
           e.printStackTrace();
           return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
       }
    }

    /**
     * 查询举报
     * @param 
     * @return
     */
    @Override
    public ResponseResult selectReport(Integer page, Integer size, Boolean isHandle) {
        try {
            QueryWrapper<KcReport> wp = new QueryWrapper<>();
            System.err.println(isHandle);;
            if(isHandle != null){

                wp.eq("is_handle", isHandle);
            }
            Page<KcReport> pageSettings = new Page<>(page, size);
            Page<KcReport> data = knowReportMapper.selectPage(pageSettings, wp);
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,data);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult delete(Long id) {
        try {
            knowReportMapper.deleteById(id);
            return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult change(Long id) {
        try {
            KcReport knw = knowReportMapper.selectById(id);
            knw.setIsHandle(true);
            knowReportMapper.updateById(knw);
            return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

}
