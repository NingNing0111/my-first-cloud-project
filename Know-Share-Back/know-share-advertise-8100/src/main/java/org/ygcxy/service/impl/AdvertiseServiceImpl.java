package org.ygcxy.service.impl;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.dto.AdvertiseRequest;
import org.ygcxy.entity.Advertise;
import org.ygcxy.logs.RecordLogs;
import org.ygcxy.repository.AdvertiseRepository;
import org.ygcxy.service.AdvertiseService;

import java.sql.Date;
import java.util.HashMap;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 13:54
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdvertiseServiceImpl implements AdvertiseService {
    private final AdvertiseRepository advertiseRepository;
    private final RecordLogs recordLogs;
    @Override
    public ResponseResult getAdvertise(Long id) {
        try {
            Advertise adv = advertiseRepository.findAdvertiseById(id);
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,adv);

        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(),"advertise","System Error");
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult getAllAdvertisePage(Integer page, Integer size, String sortType,Boolean isShow) {
        Sort.Direction direction = Sort.Direction.ASC;
        long total = 0L;
        Page<Advertise> advertisePage = null;
        if(!StringUtils.isBlank(sortType)){
            if(sortType.equals("desc")){
                direction = Sort.Direction.DESC;
            }
        }
        try {
            PageRequest pageProperties = PageRequest.of(page, size, Sort.by(direction, "id"));
            if(isShow == null){
                total = advertiseRepository.count();
                advertisePage = advertiseRepository.findAll(pageProperties);
            }else {
                total = advertiseRepository.countAdvertisesByIsShow(isShow);
                advertisePage = advertiseRepository.findAdvertisesByIsShow(pageProperties,isShow);
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("total",total);
            data.put("advertises",advertisePage);
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,data);
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(),"advertise","System Error");
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }

    }

    @Transactional
    @Override
    public ResponseResult addAdvertise(AdvertiseRequest request) {
        Advertise adv = Advertise.builder()
                .description(request.getDescription())
                .title(request.getTitle())
                .targetUrl(request.getTargetUrl())
                .imageUrl(request.getImageUrl())
                .startDate(new Date(request.getStartDate().getTime()))
                .endDate(new Date(request.getEndDate().getTime()))
                .isShow(request.getIsShow())
                .build();
        try {
            advertiseRepository.saveAndFlush(adv);
            return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(),"advertise","System Error");
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public ResponseResult deleteAdvertise(Long id) {
        try {
            advertiseRepository.deleteAdvertiseById(id);
            return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(),"advertise","System Error");

            return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }
    }

    @Transactional
    @Override
    public ResponseResult updateAdvertise(AdvertiseRequest request,Long id) {
        try {
            Advertise adv = advertiseRepository.findAdvertiseById(id);
            adv.setDescription(request.getDescription());
            adv.setTitle(request.getTitle());
            adv.setImageUrl(request.getImageUrl());
            adv.setTargetUrl(request.getTargetUrl());
            adv.setStartDate(new Date(request.getStartDate().getTime()));
            adv.setEndDate(new Date(request.getEndDate().getTime()));
            adv.setIsShow(request.getIsShow());
            advertiseRepository.saveAndFlush(adv);
            return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(),"advertise","System Error");

            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult findAllPage(Integer page, Integer size) {
        Page<Advertise> advertisesByIsShow = advertiseRepository.findAdvertisesByIsShow(PageRequest.of(page, size), true);

        return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,advertisesByIsShow);
    }
}
