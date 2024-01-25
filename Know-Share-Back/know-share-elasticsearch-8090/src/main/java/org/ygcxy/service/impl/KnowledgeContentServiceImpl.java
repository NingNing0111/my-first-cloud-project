package org.ygcxy.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.stereotype.Service;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.dto.QueryParamRequest;
import org.ygcxy.entity.KnowledgeContent;
import org.ygcxy.logs.RecordLogs;
import org.ygcxy.repository.KnowledgeContentRepository;
import org.ygcxy.service.KnowledgeContentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 16:31
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class KnowledgeContentServiceImpl implements KnowledgeContentService {
    private final KnowledgeContentRepository knowledgeContentRepository;
    private final RecordLogs recordLogs;
    @Override
    public ResponseResult findByContent(String keyword, Integer page, Integer size) {
        try {
            Page<KnowledgeContent> result = knowledgeContentRepository.findByContentAndIsKcShow(keyword, true,PageRequest.of(page, size));
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,result);
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(), "Elasticsearch","System Error");
            return ResponseResult.setCommonStatus(StatusCode.SEARCH_ERROR);
        }
    }

    @Override
    public ResponseResult findByTitle(String keyword, Integer page, Integer size) {
        try {
            Page<KnowledgeContent> byKcTitle = knowledgeContentRepository.findByKcTitleAndIsKcShow(keyword,true, PageRequest.of(page, size));
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,byKcTitle);

        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(), "Elasticsearch","System Error");
            return ResponseResult.setCommonStatus(StatusCode.SEARCH_ERROR);

        }
    }

    @Override
    public ResponseResult findByOverview(String keyword, Integer page, Integer size) {
        try {
            Page<KnowledgeContent> byOverview = knowledgeContentRepository.findByKcOverviewAndIsKcShow(keyword,true, PageRequest.of(page, size));
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,byOverview);

        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(), "Elasticsearch","System Error");
            return ResponseResult.setCommonStatus(StatusCode.SEARCH_ERROR);
        }
    }

    @Override
    public ResponseResult findByKcCategory(List<String> kcCategory, Integer page, Integer size) {
        try {
            Page<KnowledgeContent> byKcCategory = knowledgeContentRepository.findByKcCategoryAndIsKcShow(kcCategory, true,PageRequest.of(page, size));
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,byKcCategory);
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(), "Elasticsearch","System Error");
            return ResponseResult.setCommonStatus(StatusCode.SEARCH_ERROR);

        }
    }

    @Override
    public void update(KnowledgeContent knowledgeContent) {
        try {
        KnowledgeContent knowledgeContentsByKcId = knowledgeContentRepository.findKnowledgeContentsByKcId(knowledgeContent.getKcId());
        String id = knowledgeContentsByKcId.getId();
        knowledgeContent.setId(id);
        knowledgeContentRepository.save(knowledgeContent);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public ResponseResult findAll(Integer page, Integer size) {
        Page<KnowledgeContent> knowledgeContentsByIsKcShow = knowledgeContentRepository.findKnowledgeContentsByIsKcShowOrderByKcPublishTimeDesc(PageRequest.of(page, size), true);

        return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,knowledgeContentsByIsKcShow);
    }

    @Override
    public ResponseResult findById(Long id) {
        try {
            KnowledgeContent knowledgeContent = knowledgeContentRepository.findKnowledgeContentByKcIdAndIsKcShow(id,true);
            return ResponseResult.setCommonStatusAndData(StatusCode.SEARCH_SUCCESS,knowledgeContent);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult findHotPage(Integer page, Integer size) {
        try {
            Page<KnowledgeContent> knowledgeContent = knowledgeContentRepository.findKnowledgeContentsByIsKcShowOrderByViewDesc(PageRequest.of(page, size), true);
            return ResponseResult.setCommonStatusAndData(StatusCode.SEARCH_SUCCESS,knowledgeContent);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult findCommentPage(Integer page, Integer size) {
        try {
            Page<KnowledgeContent> knowledgeContents = knowledgeContentRepository.findKnowledgeContentsByIsKcShowOrderByCommentDesc(PageRequest.of(page,size),true);
            return ResponseResult.setCommonStatusAndData(StatusCode.SEARCH_SUCCESS,knowledgeContents);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult findCollectPage(Integer page, Integer size) {
        try {
            Page<KnowledgeContent> knowledgeContents = knowledgeContentRepository.findKnowledgeContentsByIsKcShowOrderByCollectDesc(PageRequest.of(page,size),true);
            return ResponseResult.setCommonStatusAndData(StatusCode.SEARCH_SUCCESS,knowledgeContents);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseResult findLikesPage(Integer page, Integer size) {
        try {
            Page<KnowledgeContent> knowledgeContents = knowledgeContentRepository.findKnowledgeContentsByIsKcShowOrderByLikesDesc(PageRequest.of(page,size),true);
            return ResponseResult.setCommonStatusAndData(StatusCode.SEARCH_SUCCESS,knowledgeContents);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult findPageByUserId(Long userId, Integer page, Integer size) {
        try {
            Page<KnowledgeContent> contents = knowledgeContentRepository.findKnowledgeContentsByUserId(PageRequest.of(page, size), userId);
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,contents);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult queryByParam(Integer page, Integer size, QueryParamRequest request) {
        String title = request.getTitle()==null ? "" : request.getTitle();
        String overview= request.getOverview() == null ? "" : request.getOverview();
        Page<KnowledgeContent> data = null;
        if(request.getUserId() == null){
            data = knowledgeContentRepository.findKnowledgeContentsByKcTitleContainingAndKcOverviewContaining(
                    PageRequest.of(page, size),
                    title,
                    overview
            );
        }else{
            data = knowledgeContentRepository.findKnowledgeContentsByKcTitleContainingAndKcOverviewContainingAndUserId(
                    PageRequest.of(page, size),
                    title,
                    overview,
                    request.getUserId()
            );
        }

        return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,data);
    }

    @Override
    public void addLike(Long kcId) {

    }

    @Override
    public void addComment(Long kcId) {

    }

    @Override
    public void addCollect(Long kcId) {

    }
}
