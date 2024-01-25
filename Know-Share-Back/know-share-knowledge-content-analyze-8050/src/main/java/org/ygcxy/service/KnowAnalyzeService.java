package org.ygcxy.service;

import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.request.Request;

public interface KnowAnalyzeService {
    ResponseResult analyzeLikes(Request request);

    ResponseResult analyzeComments(Request request);

    ResponseResult analyzeFavorites(Request request);

    ResponseResult analyzeKnow(Request request);

    ResponseResult analyzeBanKnow(Request request);

    ResponseResult analyzeIsSensitivePercentage();

    ResponseResult analyzeSumView();

    ResponseResult countKnow();

    ResponseResult countByYear(int year);

}
