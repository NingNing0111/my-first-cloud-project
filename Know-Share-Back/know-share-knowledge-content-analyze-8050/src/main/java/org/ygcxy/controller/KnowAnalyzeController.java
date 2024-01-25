package org.ygcxy.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.comment.Application;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.request.Request;
import org.ygcxy.service.KnowAnalyzeService;

@RestController
@RequestMapping(Application.API_VERSION + "/analyzeknow")
@RequiredArgsConstructor
public class KnowAnalyzeController {

    private final KnowAnalyzeService knowAnalyzeService;

    /**
     * 点赞数据
     * @param
     * @return
     */
    @GetMapping("/like")
    public ResponseResult analyzeLikes(@RequestBody Request request){
        return knowAnalyzeService.analyzeLikes(request);
    }

    /**
     * 评论数据
     * @param request
     * @return
     */
    @GetMapping("/comment")
    public ResponseResult analyzeComment(@RequestBody Request request){
        return knowAnalyzeService.analyzeComments(request);
    }


    /**
     * 收藏统计
     * @param request
     * @return
     */
    @GetMapping("/favorite")
    public ResponseResult analyzeFavorites(@RequestBody Request request){
        return knowAnalyzeService.analyzeFavorites(request);
    }

    /**
     * 文章总数展示
     * @param request
     * @return
     */
    @GetMapping("/know")
    public ResponseResult analyzeKnow(@RequestBody Request request){
        return knowAnalyzeService.analyzeKnow(request);
    }

    /**
     * 封禁文章展示
     * @param request
     * @return
     */
    @GetMapping("/banKnow")
    public ResponseResult analyzeBanKnow(@RequestBody Request request){
        return knowAnalyzeService.analyzeBanKnow(request);
    }

    /**
     * 统计敏感率
     * @return
     */
    @GetMapping("/percentage/sensitive")
    public ResponseResult querySensitive(){
        return knowAnalyzeService.analyzeIsSensitivePercentage();
    }

    /**
     * 统计总观看量
     * @return
     */
    @GetMapping("/sum/view")
    public ResponseResult queryViews() {
        return knowAnalyzeService.analyzeSumView();
    }

    /**
     * 统计总的知识内容量
     * @return
     */
    @GetMapping("/count/know")
    public ResponseResult countKnow() {
        return knowAnalyzeService.countKnow();
    }

    /**
     * 统计某年每一月的知识内容发布量
     * @param year
     * @return
     */
    @GetMapping("/year/{year}")
    public ResponseResult countByYear(
            @PathVariable int year
    ) {
        return knowAnalyzeService.countByYear(year);
    }
}
