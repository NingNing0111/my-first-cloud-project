package org.ygcxy.service;


import org.ygcxy.common.ResponseResult;
import org.ygcxy.dto.AdvertiseRequest;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 11:37
 * @Description:
 */
public interface AdvertiseService {
    ResponseResult getAdvertise(Long id);
    ResponseResult getAllAdvertisePage(Integer page, Integer size,String sortType,Boolean isShow);

    ResponseResult addAdvertise(AdvertiseRequest request);

    ResponseResult deleteAdvertise(Long id);

    ResponseResult updateAdvertise(AdvertiseRequest request,Long id);

    ResponseResult findAllPage(Integer page, Integer size);
}
