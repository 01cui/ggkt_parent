package com.cui.ggkt.vod.controller;

import com.cui.R.Result;
import com.cui.ggkt.vod.service.VisitorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@RestController
@RequestMapping("/vod/visitor")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("statistics/{courseId}/{startTime}/{endTime}")
    public Result<Map<String,Object>> statistics(@PathVariable String courseId,
                                                 @PathVariable String startTime,
                                                 @PathVariable String endTime){
        return Result.ok(visitorService.statistics(courseId,startTime,endTime));
    }

}