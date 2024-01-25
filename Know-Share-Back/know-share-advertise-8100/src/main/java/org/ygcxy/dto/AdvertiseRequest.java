package org.ygcxy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 11:38
 * @Description:
 */
@Data
public class AdvertiseRequest {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String title;
    private String description;
    private String imageUrl;
    private String targetUrl;
    private Boolean isShow;
}
