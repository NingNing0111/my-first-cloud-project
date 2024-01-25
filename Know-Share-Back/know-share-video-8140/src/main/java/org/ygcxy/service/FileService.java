package org.ygcxy.service;

import jakarta.annotation.Resource;
import org.ygcxy.common.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResponseResult insertFile(MultipartFile file, Long userId);

    ResponseResult queryFileByUserId(Long userId);

    ResponseResult deleteFileById(Long id);
}
