package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.common.Application;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.dto.SensitiveServiceResponse;
import org.ygcxy.dto.TextWord;
import org.ygcxy.service.SensitiveWordService;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 10:06
 * @Description:
 */
@RestController
@RequestMapping(Application.API_VERSION + "/sensitive")
@RequiredArgsConstructor
public class SensitiveCheckController {
    private final SensitiveWordService service;
    @PostMapping("/check")
    public ResponseResult check(@RequestBody TextWord text){
        SensitiveServiceResponse sensitiveServiceResponse = service.sensitiveService(text.getText());
        return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,sensitiveServiceResponse);
    }
    @PostMapping("/add")
    public ResponseResult add(@RequestBody TextWord word){
        return service.addWord(word.getWord());
    }
    @PutMapping("/update")
    public ResponseResult update(){
        return service.updateWord();
    }
    @DeleteMapping("/{word}")
    public ResponseResult delete(
            @PathVariable String word
    ){
        return service.deleteByWord(word);
    }
}
