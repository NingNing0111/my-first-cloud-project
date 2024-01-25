package org.ygcxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.comment.Application;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.request.KnowDtoRequest;
import org.ygcxy.entity.KnowledgeContent;
import org.ygcxy.service.KnowService;

@RestController
@RequestMapping(Application.API_VERSION + "/know")
@RequiredArgsConstructor
public class KnowController {
    private final KnowService knowService;

    /**
     * 显示文章详情
     * @param
     * @return
     */
    @GetMapping("/detail/{id}")
    public ResponseResult getKnowById(
            @PathVariable Long id
    ){

        return knowService.getKnowById(id);
    }

    /**
     *添加文章
     */
    @PostMapping("/add")
    public ResponseResult addKnow(@RequestBody KnowDtoRequest request){
        System.err.println(request.getKcImage());
        return knowService.addKnow(request);
    }

    /**
     * 分页
     * @return
     */
    @GetMapping("/page/{page}/size/{size}")
    public Page<KnowledgeContent> listPage(
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowService.listPage(page,size);
    }

    @GetMapping("/{userId}/page/{page}/size/{size}")
    public Page<KnowledgeContent> listPageByUserId(
            @PathVariable Integer userId,
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowService.listPage(page,size);
    }

    /**
     * 修改文章显示状态
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseResult deleteKnow(
            @PathVariable("id") Integer id
    ){
        return knowService.deleteKnow(id);
    }

    /**
     * 更新知识
     * @param request
     * @return
     */
    @PutMapping("/")
    public ResponseResult updateKnow(
            @RequestBody KnowDtoRequest request
    ){
        return knowService.updateKnow(request);
    }

    @GetMapping("/collect/{userId}/page/{page}/size/{size}")
    public ResponseResult queryCollectByPage(
            @PathVariable Long userId,
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowService.queryCollectPageByUserId(page,size,userId);
    }

}
