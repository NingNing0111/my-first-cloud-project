package org.ygcxy.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.ygcxy.common.Application;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.dto.SensitiveServiceResponse;
import org.ygcxy.entity.SensitiveWord;
import org.ygcxy.repository.SensitiveWordRepository;
import org.ygcxy.service.SensitiveWordService;
import toolgood.words.StringSearch;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 09:46
 * @Description:
 */
@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class SensitiveWordServiceImpl implements SensitiveWordService {
    private final SensitiveWordRepository sensitiveWordRepository;
    private final StringSearch stringSearch;
    @Override
    public SensitiveServiceResponse sensitiveService(String str) {
        Boolean isSensitive = false;
        String statement = str;
        if (stringSearch.ContainsAny(str)){
            isSensitive = true;
            statement = stringSearch.Replace(str, Application.replaceChar);
        }
        return SensitiveServiceResponse.builder()
                .statement(statement)
                .isSensitive(isSensitive)
                .build();
    }

    @Override
    public ResponseResult addWord(String word) {
        SensitiveWord sensitiveWord = new SensitiveWord();
        sensitiveWord.setWord(word);
        sensitiveWord.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        sensitiveWord.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        sensitiveWordRepository.save(sensitiveWord);
        return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
    }

    @Override
    public ResponseResult updateWord() {
        initWord();
        return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
    }

    @Transactional
    @Override
    public ResponseResult deleteByWord(String word) {
        sensitiveWordRepository.removeByWord(word);
        return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
    }

    @Scheduled(cron = "0 0 8 ? * *")
    public void initWord(){
        try {
            stringSearch.SetKeywords(getSensitiveWords());
            log.info("词库加载成功！");
        }catch (Exception e){
            log.info("敏感词库加载过程中出现异常：{}",e.getMessage());
        }
    }

    @PostConstruct
    public void init(){
        initWord();
    }

    private List<String> getSensitiveWords(){
        List<SensitiveWord> sensitiveWordList = sensitiveWordRepository.findAll();
        List<String> sensitiveWords = new ArrayList<>();
        sensitiveWordList.forEach(e -> {
            sensitiveWords.add(e.getWord());
        });
        return sensitiveWords;
    }
}
