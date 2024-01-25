package org.ygcxy.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.comment.Application;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.request.FavoritesRequest;
import org.ygcxy.service.FavoritesService;

@Slf4j
@RequestMapping(Application.API_VERSION + "/collect")
@RestController
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoritesService favoritesService;

    @PostMapping("/")
    public ResponseResult collect(@RequestBody FavoritesRequest request){
        return favoritesService.collectKnow(request);
    }
}
