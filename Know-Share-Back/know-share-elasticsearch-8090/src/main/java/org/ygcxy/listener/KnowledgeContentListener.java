package org.ygcxy.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.ygcxy.common.RabbitMqConstant;
import org.ygcxy.dto.RMDto;
import org.ygcxy.entity.KnowledgeContent;
import org.ygcxy.repository.KnowledgeContentRepository;
import org.ygcxy.service.KnowledgeContentService;

import java.sql.Date;

/**
 * @Project: org.ygcxy.listener
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 19:56
 * @Description:
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class KnowledgeContentListener {
    private final KnowledgeContentRepository repository;
    private final KnowledgeContentService knowledgeContentService;
    @RabbitListener(queues = RabbitMqConstant.WORK_ES_ADD_CONTENT_QUEUE)
    public void add(KnowledgeContent knowledgeContent){
        knowledgeContent.setLikes(0L);
        knowledgeContent.setComment(0L);
        knowledgeContent.setCollect(0L);
        repository.save(knowledgeContent);
    }
    @RabbitListener(queues = RabbitMqConstant.WORK_ES_UPDATE_CONTENT_QUEUE)
    public void update(KnowledgeContent knowledgeContent){
        knowledgeContentService.update(knowledgeContent);
    }
    @RabbitListener(queues = RabbitMqConstant.WORK_ES_DELETE_CONTENT_QUEUE)
    public void delete(Long kcId){
        repository.deleteAllByKcId(kcId);
    }

    @RabbitListener(queues = RabbitMqConstant.WORK_ES_LIKES_CONTENT_QUEUE)
    public void like(RMDto rmDto) {
        KnowledgeContent content = repository.findByKcId(rmDto.getKcId());
        log.error("知识内容ID:{}",rmDto.getKcId());
//        if(content == null && content.getLikes()==null) return;
        log.error("点赞前:{},收藏量:{},点赞量:{}",rmDto.getIsOk(),content.getCollect(),content.getLikes());
        if(rmDto.getIsOk()){
            content.setLikes(content.getLikes() + 1);
        }else{
            content.setLikes(content.getLikes() - 1);
        }
        knowledgeContentService.update(content);
    }

    @RabbitListener(queues =  RabbitMqConstant.WORK_ES_COLLECT_CONTENT_QUEUE)
    public void collect(RMDto rmDto) {
        KnowledgeContent content = repository.findKnowledgeContentsByKcId(rmDto.getKcId());
        log.error("知识内容ID:{},收藏前:{},收藏数量:{},点赞量:{}",rmDto.getKcId(),rmDto.getIsOk(),content.getCollect(),content.getLikes());
        if(rmDto.getIsOk()){
            content.setCollect(content.getCollect() + 1);
        }else{
            content.setCollect(content.getCollect() - 1);
        }
        knowledgeContentService.update(content);
    }

    @RabbitListener(queues = RabbitMqConstant.WORK_ES_COMMENT_CONTENT_QUEUE)
    public void comment(RMDto rmDto) {
        KnowledgeContent content = repository.findKnowledgeContentsByKcId(rmDto.getKcId());
        content.setComment(content.getComment() + 1);
        knowledgeContentService.update(content);
    }

}
