package com.cui.ggkt.vod.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.ggkt.model.vod.CourseDescription;
import com.cui.ggkt.vod.mapper.DescriptionMapper;
import com.cui.ggkt.vod.service.DescriptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Service
public class DescriptionServiceImp extends ServiceImpl<DescriptionMapper, CourseDescription> implements DescriptionService {

}