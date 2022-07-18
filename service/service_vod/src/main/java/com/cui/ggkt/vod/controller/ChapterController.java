package com.cui.ggkt.vod.controller;

import com.cui.R.Result;
import com.cui.ggkt.model.vod.Chapter;
import com.cui.ggkt.vo.vod.ChapterVo;
import com.cui.ggkt.vod.service.ChapterService;
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
 * 课程 前端控制器
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@RestController
@RequestMapping("/vod/chapter")
public class ChapterController {

    private final ChapterService chapterService;


    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("querySectionList/{courseId}")
    public Result<List<ChapterVo>> querySectionList(@PathVariable Long courseId) {
        return Result.ok(chapterService.querySectionList(courseId));
    }

    @PostMapping("saveChapter")
    public Result<Void> saveChapter(@RequestBody ChapterVo chapterVo) {
        chapterService.saveChapter(chapterVo);
        return Result.ok();
    }

    @GetMapping("getChapterById/{id}")
    public Result<ChapterVo> getChapterById(@PathVariable Long id) {
        return Result.ok(chapterService.getChapterById(id));
    }

    @PutMapping("updateChapter")
    public Result<Void> updateChapter(@RequestBody Chapter chapter) {
        chapterService.updateChapter(chapter);
        return Result.ok();
    }

    @DeleteMapping("deleteChapter/{id}")
    public Result<Void> deleteChapter(@PathVariable Long id) {
        chapterService.deleteChapter(id);
        return Result.ok();
    }


}