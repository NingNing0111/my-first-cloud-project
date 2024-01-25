package org.ygcxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.ygcxy.Dao.ResourceDao;
import org.ygcxy.common.Application;
import org.ygcxy.common.FileType;
import org.ygcxy.entity.Resource;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.config.MinioConfig;
import org.ygcxy.config.MinioProperties;
import org.ygcxy.feign.UserFeign;
import org.ygcxy.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.ygcxy.utils.CheckFileTypeUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final MinioConfig minioConfig;
    private final MinioProperties minioProperties;
    private final ResourceDao resourceDao;
    //上传文件
    @Override
    public ResponseResult insertFile(MultipartFile file, Long userId) {
        try{
            MinioClient minioClient = minioConfig.minioClient();
            // 获取文件类型
            byte[] bytes = file.getBytes();
            FileType type = CheckFileTypeUtils.getType(bytes);
            //生成随机文件名
            String filename = Application.APP_NAME + "-" + file.getOriginalFilename();
            //使用putObject上传文件
            minioClient.putObject(PutObjectArgs.builder()
                            .bucket(minioProperties.getBucketName())
                            .object(filename)
                            .stream(file.getInputStream(),file.getSize(),-1)
                            .contentType(file.getContentType())
                    .build());

            Resource resource = Resource.builder()
                    .userId(userId)
                    .url(minioProperties.getEndpoint()+minioProperties.getBucketName()+"/"+filename)
                    .type(type == null ? "未知" : type.name())
                    .name(filename)
                    .build();
            resourceDao.insert(resource);
            Map<String, String> data = new HashMap<>();
            data.put("url",minioProperties.getEndpoint()+minioProperties.getBucketName()+"/"+filename);
            data.put("name",filename);
            data.put("type",resource.getType());
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,data);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.setCommonStatus(StatusCode.INSERT_FILE_ERROR);
        }
    }

    @Override
    public ResponseResult queryFileByUserId(Long userId) {
        try {
            QueryWrapper<Resource> wp = new QueryWrapper<>();
            wp.eq("user_id",userId);
            List<Resource> resources = resourceDao.selectList(wp);
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,resources);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.setCommonStatus(StatusCode.INSERT_FILE_ERROR);
        }
    }

    @Override
    public ResponseResult deleteFileById(Long id) {
        try {
            resourceDao.deleteById(id);
            return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INSERT_FILE_ERROR);
        }
    }

}
