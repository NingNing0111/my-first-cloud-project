package org.ygcxy.service;

import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.CommentDto;
import org.ygcxy.dto.request.CommentsRequest;

public interface CommentsService {
    ResponseResult getList();

    ResponseResult getCommentByKcId(Long id);

    void setChildren(CommentDto newCommentDto);

    ResponseResult getCommentByparentId(CommentsRequest request);

    ResponseResult addComment(CommentsRequest request);
}
