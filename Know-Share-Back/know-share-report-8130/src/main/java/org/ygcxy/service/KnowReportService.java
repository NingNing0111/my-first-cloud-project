package org.ygcxy.service;

import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.request.Request;

public interface KnowReportService {
    ResponseResult report(Request request);

    ResponseResult selectReport(Integer page, Integer size, Boolean isHandle);

    ResponseResult delete(Long id);

    ResponseResult change(Long id);
}
