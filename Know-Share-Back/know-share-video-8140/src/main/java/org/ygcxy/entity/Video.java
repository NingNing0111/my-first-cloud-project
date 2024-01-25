package org.ygcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "video", autoResultMap = true)
public class Video {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String uploadId; //分片上传uploadId
    private String fileIdentifier; //文件唯一标识
    private String fileName; //文件名
    private String bucketName; //所属桶名
    private String objectKey; //文件的key
    private Long totalSize; //文件大小
    private Long chunkSize; //每个分片大小
    private Integer chunkNum; //分片数量
}
