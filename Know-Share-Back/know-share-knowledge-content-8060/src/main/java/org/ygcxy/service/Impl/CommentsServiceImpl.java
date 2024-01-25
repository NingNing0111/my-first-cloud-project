package org.ygcxy.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ygcxy.comment.RabbitMqConstant;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.comment.StatusCode;
import org.ygcxy.dto.CommentDto;
import org.ygcxy.dto.RMDto;
import org.ygcxy.dto.request.CommentsRequest;
import org.ygcxy.entity.*;
import org.ygcxy.feign.UserFeign;
import org.ygcxy.feign.UserMessageFeign;
import org.ygcxy.mapper.CommentsMapper;
import org.ygcxy.mapper.KnowMapper;
import org.ygcxy.mapper.UserMapper;
import org.ygcxy.service.CommentsService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private final CommentsMapper commentsMapper;
    private final RabbitTemplate rabbitTemplate;
    private final UserFeign userFeign;
    private final UserMessageFeign messageFeign;

    @Override
    public ResponseResult getList() {
        QueryWrapper<Comments> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("is_parent_comment_id", 0L);
        List<Comments> comments = commentsMapper.selectList(queryWrapper);
        List<CommentDto> newCommentDtos = new ArrayList<>();
        for (Comments newComments : comments){
            User user = userFeign.findUserById(newComments.getUserId());
            CommentDto commentDto = CommentDto.builder()
                    .commentsId(newComments.getCommentsId())
                    .userNickname(user.getUserNickname())
                    .commentsContent(newComments.getCommentsContent())
                    .userNickname(user.getUserNickname())
                    .userAvatarUrl(user.getUserAvatarUrl())
                    .commentsTime(newComments.getCommentsTime())
                    .userId(newComments.getUserId())
                    .kcId(newComments.getKcId())
                    .build();
            newCommentDtos.add(commentDto);
        }
        newCommentDtos.forEach(this::setChildren);
        return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,newCommentDtos);
    }

    /**
     * 显示所有评论
     * @return
     */
    @Override
    public ResponseResult getCommentByKcId(Long kcId) {
        try{
            QueryWrapper<Comments> queryWrapper =new QueryWrapper<>();
            queryWrapper.eq("kc_id",kcId);
            queryWrapper.eq("is_parent_comment_id", 0);
            List<Comments> comments = commentsMapper.selectList(queryWrapper);
            List<CommentDto> newCommentDtos = new ArrayList<>();
            for (Comments newComments : comments){
                User user = userFeign.findUserById(newComments.getUserId());
                CommentDto commentDto = CommentDto.builder()
                        .commentsId(newComments.getCommentsId())
                        .userNickname(user.getUserNickname())
                        .commentsContent(newComments.getCommentsContent())
                        .userNickname(user.getUserNickname())
                        .userAvatarUrl(user.getUserAvatarUrl())
                        .commentsTime(newComments.getCommentsTime())
                        .userId(newComments.getUserId())
                        .kcId(newComments.getKcId())
                        .build();
                newCommentDtos.add(commentDto);
            }
            newCommentDtos.forEach(this::setChildren);
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,newCommentDtos);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 递归获取
     * @param
     */
    @Override
    public void setChildren(CommentDto newCommentDto){
        QueryWrapper<Comments> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("kc_id",newCommentDto.getKcId());
        queryWrapper.eq("is_parent_comment_id", newCommentDto.getCommentsId());
        List<Comments> comments = commentsMapper.selectList(queryWrapper);
        List<CommentDto> newCommentDtos = new ArrayList<>();
        for (Comments newComments : comments){
            User user = userFeign.findUserById(newComments.getUserId());
            CommentDto commentDto = CommentDto.builder()
                    .commentsId(newComments.getCommentsId())
                    .userNickname(user.getUserNickname())
                    .commentsContent(newComments.getCommentsContent())
                    .userNickname(user.getUserNickname())
                    .userAvatarUrl(user.getUserAvatarUrl())
                    .commentsTime(newComments.getCommentsTime())
                    .userId(newComments.getUserId())
                    .kcId(newComments.getKcId())
                    .build();

            newCommentDtos.add(commentDto);
        }
        if (!newCommentDtos.isEmpty()) {
            newCommentDto.setChildComment(newCommentDtos);
            newCommentDtos.forEach(this::setChildren);
        }
    }




    @Override
    public ResponseResult getCommentByparentId(CommentsRequest request) {
        return null;
    }

    @Override
    public ResponseResult addComment(CommentsRequest request) {

        Comments comment = Comments.builder()
                .commentsContent(request.getCommentsContent())
                .commentsTime(new Date())
                .userId(request.getUserId())
                .kcId(request.getKcId())
                .isParentCommentId(request.getIsParentCommentId() == null ? 0 : request.getIsParentCommentId())
                .build();

        commentsMapper.insert(comment);
        rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_ES_COMMENT_CONTENT_QUEUE,RMDto.builder().kcId(request.getKcId()).isOk(true).build());
        messageFeign.add(
                UserMessage.builder()
                        .userId(request.getUserId())
                        .kcId(request.getKcId())
                        .type(UserMessageType.COMMENT)
                        .build()
        );
        return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
    }
}