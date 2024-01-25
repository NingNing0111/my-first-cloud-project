package org.ygcxy.service;

import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.request.FavoritesRequest;

public interface FavoritesService {
    //收藏
    ResponseResult collectKnow(FavoritesRequest request);
}
