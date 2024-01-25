package org.ygcxy.dto.request;

import lombok.Data;

import java.sql.Date;

@Data
public class Request {
    private Date startTime;
    private Date endTime;
}
