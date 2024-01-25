package org.ygcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ygcxy.comment.RabbitMqConstant;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.comment.StatusCode;
import org.ygcxy.dto.RMDto;
import org.ygcxy.dto.request.LikesDtoRequest;
import org.ygcxy.entity.Likes;
import org.ygcxy.entity.UserMessage;
import org.ygcxy.entity.UserMessageType;
import org.ygcxy.feign.UserMessageFeign;
import org.ygcxy.mapper.LikesMapper;
import org.ygcxy.service.LikeService;

import javax.swing.text.StyledEditorKit;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikesMapper likesMapper;
    private final RabbitTemplate rabbitTemplate;
    private final UserMessageFeign messageFeign;

    @Override
    public ResponseResult likeKnow(LikesDtoRequest request) {
        try {

        //查询用户是否点赞过文章
        QueryWrapper<Likes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kc_id",request.getKcId());
        queryWrapper.eq("user_id",request.getUserId());
        Likes likes = likesMapper.selectOne(queryWrapper);
        Boolean isOK = false;
        ResponseResult result = null;
        if (likes!=null){
            likes.setIsLikeShow(!likes.getIsLikeShow());
            isOK = likes.getIsLikeShow();
            likesMapper.updateById(likes);
            result = likes.getIsLikeShow() ?
                    ResponseResult.setCommonStatus(StatusCode.LIKES_SUCCESS): ResponseResult.setCommonStatus(StatusCode.UN_LIKES_SUCCESS);
        }else {
            //如果未找到记录，则添加数据，点赞数加一
            Likes likes2 = new Likes();
            likes2.setKcId(request.getKcId());
            likes2.setUserId(request.getUserId());
            likes2.setLikeTime(new Date());
            likes2.setIsLikeShow(true);
            //添加记录
            likesMapper.insert(likes2);
            isOK = true;
            result = ResponseResult.setCommonStatus(StatusCode.LIKES_SUCCESS);
        }
        if(isOK){
            messageFeign.add(
                    UserMessage.builder()
                            .userId(request.getUserId())
                            .kcId(request.getKcId())
                            .type(UserMessageType.LIKE)
                            .build()
            );
        }
        rabbitTemplate.convertAndSend(
                RabbitMqConstant.WORK_ES_LIKES_CONTENT_QUEUE,
                RMDto.builder().kcId(request.getKcId()).isOk(isOK).build()
        );
        return result;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }

    }
}
