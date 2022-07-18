package com.cui.ggkt.vod.copy;

import com.cui.ggkt.model.vod.Video;
import com.cui.ggkt.vo.vod.VideoVo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author 崔令雨
 * {@code @date} 2022/7/15 9:39
 * {@code @Version} 1.0
 */
@Mapper(componentModel = "spring")
public interface VideoMapping {

    VideoVo toVideoVo(Video video);

    List<VideoVo> toVideoList(List<Video> videos);
}