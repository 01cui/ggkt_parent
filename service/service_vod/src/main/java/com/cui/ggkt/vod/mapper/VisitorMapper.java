package com.cui.ggkt.vod.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cui.ggkt.model.vod.VideoVisitor;
import com.cui.ggkt.vo.vod.VideoVisitorCountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Mapper
public interface VisitorMapper extends BaseMapper<VideoVisitor> {

    /**
     * 统计数据
     *
     * @param courseId  进程id
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return {@link List}<{@link VideoVisitorCountVo}>
     */
    List<VideoVisitorCountVo> statistics(@Param("courseId") String courseId, @Param("startTime") String startTime, @Param("endTime") String endTime);
}