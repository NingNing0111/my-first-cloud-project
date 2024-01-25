package org.ygcxy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.request.KnowDtoRequest;
import org.ygcxy.entity.KnowledgeContent;

public interface KnowService {

    /**
     * 显示文章详情
     *
     * @param
     * @return
     */
    ResponseResult getKnowById(Long id);

    /**
     * 添加文章
     * @param
     * @return
     */
    ResponseResult addKnow(KnowDtoRequest request);

    //分页
    Page<KnowledgeContent> listPage(Integer page, Integer size);

    //删除
    ResponseResult deleteKnow(Integer id);

    //更新
    ResponseResult updateKnow(KnowDtoRequest request);

    //点赞
//    ResultResponse likeKnow(LikesDtoRequest request);

    // 根据用户ID查询该用户的所有知识内容收藏数
    ResponseResult queryCollectPageByUserId(Integer page, Integer size, Long userId);

}
