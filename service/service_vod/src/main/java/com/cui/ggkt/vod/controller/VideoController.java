package com.cui.ggkt.vod.controller;

import com.cui.R.Result;
import com.cui.ggkt.model.vod.Video;
import com.cui.ggkt.vo.vod.VideoVo;
import com.cui.ggkt.vod.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@RestController
@RequestMapping("/vod/video")
@Slf4j
public class VideoController {


    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("getVideoList/{chapterId}")
    public Result<List<VideoVo>> getVideoList(@PathVariable Long chapterId) {
        return Result.ok(videoService.getVideoList(chapterId));
    }

    @PostMapping("saveVideo")
    public Result<Void> saveVideo(@RequestBody Video video) {
        log.info("接收小节参数:{}", video);
        videoService.saveVideo(video);
        return Result.ok();
    }

    @GetMapping("getVideoById/{id}")
    public Result<Video> getVideoById(@PathVariable("id") String id) {
        return Result.ok(videoService.getById(id));
    }

    @PutMapping("updateVideo")
    public Result<Void> updateVideo(@RequestBody Video video) {
        boolean b = videoService.updateById(video);

        if (!b) {
            return Result.fail();
        }
        return Result.ok();
    }

    @DeleteMapping("deleteVideo/{id}")
    public Result<Void> deleteVideo(@PathVariable("id") Long id) {
        videoService.deleteVideo(id);
        return Result.ok();
    }

    @PostMapping("uploadVideo")
    public Result<String> uploadVideo() {
        return Result.ok(videoService.uploadVideo());
    }


}