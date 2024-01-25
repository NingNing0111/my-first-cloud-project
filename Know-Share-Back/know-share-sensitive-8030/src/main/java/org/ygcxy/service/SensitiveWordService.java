package org.ygcxy.service;

import org.ygcxy.common.ResponseResult;
import org.ygcxy.dto.SensitiveServiceResponse;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 09:41
 * @Description:
 */
public interface SensitiveWordService {
    SensitiveServiceResponse sensitiveService(String str);
    ResponseResult addWord(String word);

    ResponseResult updateWord();

    ResponseResult deleteByWord(String word);
}
