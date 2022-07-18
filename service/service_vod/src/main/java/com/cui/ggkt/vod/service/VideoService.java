package com.cui.ggkt.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.ggkt.model.vod.Video;
import com.cui.ggkt.vo.vod.VideoVo;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
public interface VideoService extends IService<Video> {

    /**
     * 获取视频列表
     *
     * @param chapterId 章id
     * @return {@link List}<{@link VideoVo}>
     */
    List<VideoVo> getVideoList(Long chapterId);

    /**
     * 保存视频
     *
     * @param video 视频
     */
    void saveVideo(Video video);

    /**
     * 上传视频
     *
     * @return {@link String}
     */
    String uploadVideo();

    /**
     * 删除视频
     *
     * @param id id
     * @return boolean
     */
    boolean deleteVideo(Long id);
}