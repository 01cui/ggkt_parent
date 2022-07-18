package com.cui.ggkt.vod.copy;

import com.cui.ggkt.model.vod.Chapter;
import com.cui.ggkt.vo.vod.ChapterVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author 崔令雨
 * {@code @date} 2022/7/15 8:53
 * {@code @Version} 1.0
 */
@Mapper(componentModel = "spring")
public interface ChapterMapping {

    List<ChapterVo> toChapterVoList(List<Chapter> source);

    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "param", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    Chapter toChapter(ChapterVo chapterVo);

    @Mapping(target = "children", ignore = true)
    ChapterVo toChapterVo(Chapter chapter);
}