package com.cui.ggkt.vod.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.ggkt.model.vod.CourseCollect;
import com.cui.ggkt.vod.mapper.CollectMapper;
import com.cui.ggkt.vod.service.CollectService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程收藏 服务实现类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Service
public class CollectServiceImp extends ServiceImpl<CollectMapper, CourseCollect> implements CollectService {

}