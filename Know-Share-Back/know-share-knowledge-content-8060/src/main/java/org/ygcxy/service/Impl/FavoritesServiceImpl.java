package org.ygcxy.service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.ygcxy.comment.RabbitMqConstant;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.comment.StatusCode;
import org.ygcxy.dto.RMDto;
import org.ygcxy.dto.request.FavoritesRequest;
import org.ygcxy.entity.Favorites;
import org.ygcxy.entity.UserMessage;
import org.ygcxy.entity.UserMessageType;
import org.ygcxy.feign.UserMessageFeign;
import org.ygcxy.mapper.FavoritesMapper;
import org.ygcxy.service.FavoritesService;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoritesServiceImpl implements FavoritesService {

    private final FavoritesMapper favoritesMapper;
    private final RabbitTemplate rabbitTemplate;
    private final UserMessageFeign messageFeign;
    @Override
    public ResponseResult collectKnow(FavoritesRequest request) {
        QueryWrapper<Favorites> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kc_id",request.getKcId());
        queryWrapper.eq("user_id", request.getUserId());
        Favorites favorites = favoritesMapper.selectOne(queryWrapper);
        ResponseResult result;
        Boolean isOk = false;
        if (favorites!=null) {
            favorites.setIsFavoritesShow(!favorites.getIsFavoritesShow());
            favoritesMapper.updateById(favorites);
            result = favorites.getIsFavoritesShow() ? ResponseResult.setCommonStatus(StatusCode.COLLECT_SUCCESS) : ResponseResult.setCommonStatus(StatusCode.UN_COLLECT_SUCCESS);
            isOk = favorites.getIsFavoritesShow();
        }else{

        Favorites newFavorites = new Favorites();
        newFavorites.setFavoriteTime(new Date());
        newFavorites.setKcId(request.getKcId());
        newFavorites.setUserId(request.getUserId());
        newFavorites.setIsFavoritesShow(true);
        favoritesMapper.insert(newFavorites);
        isOk = newFavorites.getIsFavoritesShow();
        result = ResponseResult.setCommonStatus(StatusCode.COLLECT_SUCCESS);
        }
        if(isOk){
            messageFeign.add(
                    UserMessage.builder()
                            .userId(request.getUserId())
                            .kcId(request.getKcId())
                            .type(UserMessageType.COLLECT)
                            .build()
            );
        }
        rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_ES_COLLECT_CONTENT_QUEUE,
                RMDto.builder().isOk(isOk).kcId(request.getKcId()).build()
        );
        return result;



    }
}
