package org.ygcxy.service;

import co.elastic.clients.elasticsearch.nodes.Ingest;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.dto.QueryParamRequest;
import org.ygcxy.entity.KnowledgeContent;

import java.util.List;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 16:29
 * @Description:
 */
public interface KnowledgeContentService {
    ResponseResult findByContent(String keyword,Integer page, Integer size);

    ResponseResult findByTitle(String keyword,Integer page,Integer size);

    ResponseResult findByOverview(String keyword,Integer page,Integer size);
    ResponseResult findByKcCategory(List<String> kcCategory, Integer page, Integer size);

    void update(KnowledgeContent knowledgeContent);

    ResponseResult findAll(Integer page, Integer size);

    ResponseResult findById(Long id);

    ResponseResult findHotPage(Integer page, Integer size);

    ResponseResult findCommentPage(Integer page, Integer size);

    ResponseResult findCollectPage(Integer page, Integer size);

    ResponseResult findLikesPage(Integer page, Integer size);

    ResponseResult findPageByUserId(Long userId, Integer page, Integer size);

    ResponseResult queryByParam (Integer page, Integer size, QueryParamRequest request);

    void addLike(Long kcId);

    void addComment(Long kcId);

    void addCollect(Long kcId);
}
