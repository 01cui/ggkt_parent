package com.cui.ggkt.vod.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cui.ggkt.model.vod.CourseDescription;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程简介 Mapper 接口
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Mapper
public interface DescriptionMapper extends BaseMapper<CourseDescription> {

}