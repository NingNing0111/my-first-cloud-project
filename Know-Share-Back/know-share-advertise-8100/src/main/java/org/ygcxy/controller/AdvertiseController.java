package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.common.Application;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.dto.AdvertiseRequest;
import org.ygcxy.service.AdvertiseService;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 14:36
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(Application.API_VERSION + "/advertise")
public class AdvertiseController {
    private final AdvertiseService advertiseService;
    @GetMapping("/{id}")
    public ResponseResult findAdvertiseById(@PathVariable("id") Long id){
        return advertiseService.getAdvertise(id);
    }
    @GetMapping("/page/{page}/size/{size}")
    public ResponseResult findAdvertisePage(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size,
            @RequestParam(name = "type", defaultValue = "asc") String sortType,
            @RequestParam(name = "isShow", required = false) Boolean isShow
    ){
        return advertiseService.getAllAdvertisePage(page,size,sortType,isShow);
    }
    @PostMapping("/add")
    public ResponseResult addAdvertise(@RequestBody AdvertiseRequest request){
        return advertiseService.addAdvertise(request);
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteAdvertise(@PathVariable("id") Long id){
        return advertiseService.deleteAdvertise(id);
    }
    @PutMapping("/{id}")
    public ResponseResult updateAdvertise(
            @RequestBody AdvertiseRequest request,
            @PathVariable("id") Long id
    ){
        return advertiseService.updateAdvertise(request, id);
    }

    @GetMapping("/all/page/{page}/size/{size}")
    public ResponseResult findAll(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
    ){
        return advertiseService.findAllPage(page,size);
    }
}
