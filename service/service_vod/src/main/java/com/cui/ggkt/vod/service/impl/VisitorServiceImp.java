package com.cui.ggkt.vod.service.impl;


import com.cui.ggkt.model.vod.VideoVisitor;
import com.cui.ggkt.vo.vod.VideoVisitorCountVo;
import com.cui.ggkt.vod.mapper.VisitorMapper;
import com.cui.ggkt.vod.service.VisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Service
public class VisitorServiceImp extends ServiceImpl<VisitorMapper, VideoVisitor> implements VisitorService {

    @Override
    public Map<String, Object> statistics(String courseId, String startTime, String endTime) {

        List<VideoVisitorCountVo> videoVisitorCountVos = baseMapper.statistics(courseId, startTime, endTime);

        List<Date> dateList = videoVisitorCountVos
                .stream()
                .map(VideoVisitorCountVo::getJoinTime)
                .collect(Collectors.toList());
        List<Integer> collect = videoVisitorCountVos
                .stream()
                .map(VideoVisitorCountVo::getUserCount)
                .collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("xData", dateList);
        map.put("yData", collect);
        return map;
    }
}