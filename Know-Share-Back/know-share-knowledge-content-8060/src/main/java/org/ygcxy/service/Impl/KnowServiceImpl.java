package org.ygcxy.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.ygcxy.comment.RabbitMqConstant;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.comment.StatusCode;
import org.ygcxy.dto.SensitiveServiceResponse;
import org.ygcxy.dto.request.KnowDtoRequest;
import org.ygcxy.entity.KnowledgeContent;
import org.ygcxy.feign.SensitiveFeign;
import org.ygcxy.mapper.KnowMapper;
import org.ygcxy.service.KnowService;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class KnowServiceImpl implements KnowService {

    private final KnowMapper knowMapper;

    private final RabbitTemplate rabbitTemplate;
    private final SensitiveFeign sensitiveFeign;

    /**
     * 显示文章详情时浏览量+1
     * @param
     * @return
     */
    @Override
    public ResponseResult getKnowById(Long id) {
        log.info("浏览量+1，文章ID:{}",id);
        try {
            KnowledgeContent knowledgeContent = knowMapper.selectById(id);
            knowledgeContent.setView(knowledgeContent.getView() + 1);
            log.info("发送前:{}",knowledgeContent);
            knowMapper.updateById(knowledgeContent);
            rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_ES_UPDATE_CONTENT_QUEUE,knowledgeContent);
            return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.setCommonStatus(StatusCode.NOT_FOUND);
        }

    }

    /**
     * 添加文章
     * @param request
     * @return
     */
    @Override
    public ResponseResult addKnow(KnowDtoRequest request) {
        KnowledgeContent know = new KnowledgeContent();

        know.setKcTitle(request.getKcTitle());
        know.setKcCategory(request.getKcCategory());
        know.setKcOverview(request.getKcOverview());
        know.setView(1L);
        know.setOriginContent(request.getContent());
        know.setImage(request.getKcImage());
        know.setUserId(request.getUserId());

        // 如果这篇文章是发表的
        if(request.getIsPublish()){
            know.setIsKcBan(true);
            know.setIsKcShow(true);
            know.setKcPublishTime(new Date(System.currentTimeMillis()));
        }else{
            know.setIsKcBan(false);
            know.setKcPublishTime(null);
            know.setIsKcShow(false);
        }

        try {
            String text = request.getContent();
            SensitiveServiceResponse checkResult = sensitiveFeign.check(text);
            Boolean isSensitive = checkResult.getIsSensitive();
            know.setContent(checkResult.getStatement());
            know.setIsSensitive(isSensitive);
            knowMapper.insert(know);
            rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_ES_ADD_CONTENT_QUEUE,know);
            if(isSensitive){
                return ResponseResult.setCommonStatus(StatusCode.ADD_IS_SENSITIVE_KNOW_SUCCESS);
            }else{
                return ResponseResult.setCommonStatus(StatusCode.ADD_KNOW_SUCCESS);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<KnowledgeContent> listPage(Integer page, Integer size) {
        Page<KnowledgeContent> knowPage = new Page<>(page,size);

        return knowMapper.selectPage(knowPage, null);
    }

    /**
     * 修改文章显示状态
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteKnow(Integer id) {
        try {
            KnowledgeContent knowledgeContent = knowMapper.selectById(id);
            knowledgeContent.setIsKcShow(!knowledgeContent.getIsKcShow());
            knowMapper.updateById(knowledgeContent);
            log.error("发送前{}",knowledgeContent);
            rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_ES_UPDATE_CONTENT_QUEUE,knowledgeContent);
            return ResponseResult.setCommonStatus(StatusCode.DELETE_KNOW_SUCCESS);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }


    }

    /**
     *  更新文章
     * @param request
     * @return
     */
    @Override
    public ResponseResult updateKnow(KnowDtoRequest request) {
        try {
            KnowledgeContent knowledgeContent = knowMapper.selectById(request.getKcId());

            knowledgeContent.setKcTitle(request.getKcTitle());
            knowledgeContent.setKcOverview(request.getKcOverview());
            knowledgeContent.setKcId(request.getKcId());
            knowledgeContent.setKcCategory(request.getKcCategory());


            String text = request.getContent();
            SensitiveServiceResponse checkResult = sensitiveFeign.check(text);
            Boolean isSensitive = checkResult.getIsSensitive();
            knowledgeContent.setContent(checkResult.getStatement());

            knowMapper.updateById(knowledgeContent);

            rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_ES_UPDATE_CONTENT_QUEUE,knowledgeContent);
            if(isSensitive){
                return ResponseResult.setCommonStatus(StatusCode.ADD_IS_SENSITIVE_KNOW_SUCCESS);
            }else{
                return ResponseResult.setCommonStatus(StatusCode.UPDATE_SUCCESS);
            }
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseResult queryCollectPageByUserId(Integer page, Integer size, Long userId) {
        try {
            Page<KnowledgeContent> knowledgeContentPage = knowMapper.queryCollectPageByUserId(userId, new Page<>(page, size));
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,knowledgeContentPage);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }


}
