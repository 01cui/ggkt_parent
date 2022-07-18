package com.cui.ggkt.vod.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cui.ggkt.model.vod.Video;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video> {

}