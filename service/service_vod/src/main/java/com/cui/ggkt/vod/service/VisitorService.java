package com.cui.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.ggkt.model.vod.VideoVisitor;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
public interface VisitorService extends IService<VideoVisitor> {

    /**
     * 统计数据
     *
     * @param courseId  进程id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    Map<String,Object> statistics(String courseId, String startTime, String endTime);
}