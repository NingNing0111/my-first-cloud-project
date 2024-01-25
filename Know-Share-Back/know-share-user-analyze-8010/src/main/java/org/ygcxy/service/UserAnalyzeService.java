package org.ygcxy.service;

import org.ygcxy.common.ResponseResult;

public interface UserAnalyzeService {

    ResponseResult countUser();

    ResponseResult countByYear(int year);
}
