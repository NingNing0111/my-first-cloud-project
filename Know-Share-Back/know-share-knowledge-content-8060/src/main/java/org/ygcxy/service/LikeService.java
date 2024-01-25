package org.ygcxy.service;

import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.request.LikesDtoRequest;

public interface LikeService {
    //点赞
    ResponseResult likeKnow(LikesDtoRequest request);
}
