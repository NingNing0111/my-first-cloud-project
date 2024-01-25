package org.ygcxy.controller;

import jakarta.annotation.Resource;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.ygcxy.common.Application;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.dto.FileRequest;
import org.ygcxy.service.FileService;
import org.ygcxy.utils.MinioUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(Application.API_VERSION + "/file")
@Slf4j
public class FileController {
    @Resource
    private MinioUtils minioUtils;
    @Resource
    private FileService fileService;

    @PostMapping("/insert")
    public ResponseResult insertFile(
            @ModelAttribute FileRequest fileRequest
    ){
        log.info("{}",fileRequest);
        return fileService.insertFile(fileRequest.getFile(), fileRequest.getUserId());
    }
    @GetMapping("/{userId}")
    public ResponseResult queryFileByUserId(
            @PathVariable Long userId
    ){
        return fileService.queryFileByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteFileById(
            @PathVariable Long id
    ){
        return fileService.deleteFileById(id);
    }

}
