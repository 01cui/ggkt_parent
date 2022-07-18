package com.cui.ggkt.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.ggkt.model.vod.Chapter;
import com.cui.ggkt.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */

public interface ChapterService extends IService<Chapter> {

    /**
     * 查询部分列表
     *
     * @param courseId 进程id
     * @return {@link List}<{@link ChapterVo}>
     */
    List<ChapterVo> querySectionList(Long courseId);

    /**
     * 添加章节
     *
     * @param chapterVo 签证官章
     */
    void saveChapter(ChapterVo chapterVo);

    /**
     * 通过id获取章
     *
     * @param id id
     * @return {@link ChapterVo}
     */
    ChapterVo getChapterById(Long id);

    /**
     * 更新一章
     *
     * @param chapter 章
     */
    void updateChapter(Chapter chapter);

    boolean deleteChapter(Long id);
}