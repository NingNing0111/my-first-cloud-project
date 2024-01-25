package org.ygcxy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.ygcxy.entity.KnowledgeContent;

import java.util.List;

/**
 * @Project: org.ygcxy.repository
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 14:51
 * @Description:
 */
@Repository
public interface KnowledgeContentRepository extends ElasticsearchRepository<KnowledgeContent,String> {
    /**
     * 根据用户ID查询知识内容
     * @param userId
     * @param pageable
     * @return
     */
    Page<KnowledgeContent> findKnowledgeContentsByUserId(Long userId, Pageable pageable);

    /**
     * 根据内容查询
     * @param content
     * @param pageable
     * @return
     */
    Page<KnowledgeContent> findByContentAndIsKcShow(String content,Boolean isKcShow,Pageable pageable);

    /**
     * 根据标签查询
     * @param kcCategory
     * @param pageable
     * @return
     */
    Page<KnowledgeContent> findByKcCategoryAndIsKcShow(List<String> kcCategory,Boolean isKcShow, Pageable pageable);

    /**
     * 根据标题查询
     * @param title
     * @param pageable
     * @return
     */
    Page<KnowledgeContent> findByKcTitleAndIsKcShow(String title,Boolean isKcShow, Pageable pageable);

    /**
     * 根据大纲查询
     * @param overview
     * @param pageable
     * @return
     */
    Page<KnowledgeContent> findByKcOverviewAndIsKcShow(String overview,boolean isKcShow, Pageable pageable);

    /**
     * 通过文章ID删除
     * @param kcId
     */
    void deleteAllByKcId(Long kcId);


    /**
     * 根据文章ID查询
     * @param kcId
     * @return
     */
    KnowledgeContent findKnowledgeContentsByKcId(Long kcId);

    /**
     * 根据文章是否可见查询
     * @param pageable
     * @param isKcShow
     * @return
     */
    Page<KnowledgeContent> findKnowledgeContentsByIsKcShowOrderByKcPublishTimeDesc(Pageable pageable,Boolean isKcShow);

    /**
     * 文章ID和是否可见
     * @param kcId
     * @param isKcShow
     * @return
     */
    KnowledgeContent findKnowledgeContentByKcIdAndIsKcShow(Long kcId,Boolean isKcShow);

    Page<KnowledgeContent> findKnowledgeContentsByIsKcShowOrderByViewDesc(Pageable pageable, Boolean isKcShow);

    Page<KnowledgeContent> findKnowledgeContentsByIsKcShowOrderByCommentDesc(Pageable pageable, Boolean isKcShow);

    Page<KnowledgeContent> findKnowledgeContentsByIsKcShowOrderByCollectDesc(Pageable pageable,Boolean isKcShow);

    Page<KnowledgeContent> findKnowledgeContentsByIsKcShowOrderByLikesDesc(Pageable pageable, Boolean isKcShow);

    Page<KnowledgeContent> findKnowledgeContentsByUserId(Pageable pageable, Long userId);

    Page<KnowledgeContent> findKnowledgeContentsByKcTitleContainingAndKcOverviewContainingAndUserId(Pageable pageable, String kcTitle, String kcOverview,Long userId);

    Page<KnowledgeContent> findKnowledgeContentsByKcTitleContainingAndKcOverviewContaining(Pageable pageable, String kcTitle, String kcOverview);

    KnowledgeContent findByKcId(Long kcId);
}
