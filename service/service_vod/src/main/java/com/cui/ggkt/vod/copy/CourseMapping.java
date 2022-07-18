package com.cui.ggkt.vod.copy;

import com.cui.ggkt.model.vod.Course;
import com.cui.ggkt.vo.vod.CourseFormVo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author 崔令雨
 * {@code @date} 2022/7/14 13:53
 * {@code @Version} 1.0
 */
@Mapper(componentModel = "spring")
public interface CourseMapping {

    List<CourseFormVo> toCourseFormVo(List<Course> source);


    Course toCourse(CourseFormVo courseFormVo);
    CourseFormVo toCourseFormVo(Course course);
}