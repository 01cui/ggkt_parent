package com.cui.ggkt.vod.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.R.ResultCodeEnum;
import com.cui.ex.SiliconValleyClassExceptionHandl;
import com.cui.ggkt.model.vod.Chapter;
import com.cui.ggkt.model.vod.Video;
import com.cui.ggkt.vo.vod.ChapterVo;
import com.cui.ggkt.vod.copy.ChapterMapping;
import com.cui.ggkt.vod.copy.VideoMapping;
import com.cui.ggkt.vod.mapper.ChapterMapper;
import com.cui.ggkt.vod.service.ChapterService;
import com.cui.ggkt.vod.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Service
@Slf4j
public class ChapterServiceImp extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    private final ChapterMapping chapterMapping;

    private final VideoService videoService;

    private final VideoMapping videoMapping;


    public ChapterServiceImp(ChapterMapping chapterMapping, VideoService videoService, VideoMapping videoMapping) {
        this.chapterMapping = chapterMapping;
        this.videoService = videoService;
        this.videoMapping = videoMapping;
    }

    @Override
    public List<ChapterVo> querySectionList(Long courseId) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        List<Chapter> chapters = baseMapper.selectList(queryWrapper);
        List<ChapterVo> chapterVos = chapterMapping.toChapterVoList(chapters);
        if (!chapters.isEmpty()) {
            List<Long> idS = new ArrayList<>();
            chapters.forEach(item -> idS.add(item.getId()));

            LambdaQueryWrapper<Video> videoLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (!idS.isEmpty()) {
                videoLambdaQueryWrapper.in(Video::getChapterId, idS);
            }
            List<Video> videos = videoService.list(videoLambdaQueryWrapper);

            chapterVos.forEach(item -> videos.forEach(video -> {
                if (Objects.equals(video.getChapterId(), item.getId())) {
                    item.getChildren().add(videoMapping.toVideoVo(video));
                }
            }));
        }
        return chapterVos;
    }

    @Override
    public void saveChapter(ChapterVo chapterVo) {
        Chapter chapter = chapterMapping.toChapter(chapterVo);
        log.info("映射之后的数据为:{}", chapter);

        int insert = baseMapper.insert(chapter);

        if (insert != 1) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.CHAPTERS_ADD_FAILURE);
        }
    }

    @Override
    public ChapterVo getChapterById(Long id) {
        return chapterMapping.toChapterVo(baseMapper.selectById(id));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateChapter(Chapter chapter) {

        int i = baseMapper.updateById(chapter);
        if (i != 1) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.UPDATE_SECTION_FAILURE);
        }
    }

    @Override
    public boolean deleteChapter(Long id) {

        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id", id);

        List<Video> list = videoService.list(videoQueryWrapper);

        if (!list.isEmpty()) {
            list.forEach(video -> {
                log.info("删除小节id:{}",video.getId());
                videoService.deleteVideo(video.getId());
            });
        }
        int i = baseMapper.deleteById(id);

        if (i != 1) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.CHAPTER_DELETE_FAIL);
        }
        return true;
    }
}