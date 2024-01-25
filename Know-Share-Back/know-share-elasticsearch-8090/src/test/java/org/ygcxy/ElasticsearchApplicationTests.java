package org.ygcxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.entity.KnowledgeContent;
import org.ygcxy.repository.KnowledgeContentRepository;
import org.ygcxy.service.KnowledgeContentService;

import java.util.List;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 15:02
 * @Description:
 */
@SpringBootTest
public class ElasticsearchApplicationTests {
    @Autowired
    private KnowledgeContentRepository repository;
    @Autowired
    private KnowledgeContentService knowledgeContentService;
    @Test
    public void addTest(){
        KnowledgeContent knowledgeContent = new KnowledgeContent();
        knowledgeContent.setKcId(1L);
        knowledgeContent.setContent("Hello,World!");
        knowledgeContent.setIsKcShow(false);
        knowledgeContent.setKcTitle("知识内容标题");
        knowledgeContent.setKcOverview("这是知识内容的概述");
        knowledgeContent.setKcCategory(List.of("测试","知识内容"));
        knowledgeContent.setUserId(26L);
        knowledgeContent.setIsKcShow(true);
        repository.save(knowledgeContent);
    }
    @Test
    public void findTest(){
        PageRequest pagePrompt = PageRequest.of(0, 10);
        Page<KnowledgeContent> contents = repository.findKnowledgeContentsByUserId(26L,pagePrompt);
        System.out.println(contents);
    }
    @Test
    public void findByContentTest(){
    }

    @Test
    public void findAllByContentTest(){
        ResponseResult result = knowledgeContentService.findByContent("知识", 0, 10);
        System.out.println(result);
    }

    @Test
    public void deleteByKcId(){
        repository.deleteAllByKcId(74L);
//        repository.deleteAll();
//        Page<KnowledgeContent> result = repository.findKnowledgeContentsByIsKcShowOrderByView(PageRequest.of(0, 10), true);
    }


    @Test
    public void sortByView() {
        Page<KnowledgeContent> result = repository.findKnowledgeContentsByIsKcShowOrderByViewDesc(PageRequest.of(0, 10), true);
        System.out.println(result);
    }

    @Test
    public void deleteTest(){
        repository.deleteAll();
    }

    @Test
    public void findByKcId(){
//        KnowledgeContent data = repository.findKnowledgeContentsByKcId(74L);
        KnowledgeContent data = repository.findByKcId(72L);
//        System.out.println(data);
        System.err.println(data.getCollect() + " " +data.getComment()+" " +data.getLikes());
    }

    @Test
    public void findByKcIdTest(){
        // 查出72的ID
        KnowledgeContent byKcId = repository.findByKcId(75L);
        // 修改72的ID的收藏量值
        byKcId.setCollect(1L);
        // 调用更新操作
        knowledgeContentService.update(byKcId);
    }

    @Test
    public void likeTest(){
        KnowledgeContent byKcId = repository.findByKcId(76L);
        byKcId.setLikes(byKcId.getLikes() + 1);
        byKcId.setComment(1L);
        knowledgeContentService.update(byKcId);
    }
}
