package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.comment.Application;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.request.Request;
import org.ygcxy.service.KnowReportService;

@RestController
@RequestMapping(Application.API_VERSION + "/report")
@RequiredArgsConstructor
public class KnowReportController {
    private final KnowReportService knowReportService;

    /**
     * 举报文章
     * @param request
     * @return
     */
    @PostMapping("/")
    public ResponseResult report(@RequestBody Request request){
        return knowReportService.report(request);
    }

    /**
     * 举报的文章信息
     * @param
     * @return
     */
    @GetMapping("/page/{page}/size/{size}")
    public ResponseResult selectReport(
            @PathVariable Integer page,
            @PathVariable Integer size,
            @RequestParam(required = false) Boolean isHandle
    ){
        return knowReportService.selectReport(page,size, isHandle);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteById(
            @PathVariable Long id
    ){
        return knowReportService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseResult change(
            @PathVariable Long id
    ){
        return knowReportService.change(id);
    }


}
