package com.cui.ggkt.vod.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.ggkt.model.vod.Course;
import com.cui.ggkt.vo.vod.CourseFormVo;
import com.cui.ggkt.vo.vod.CoursePublishVo;
import com.cui.ggkt.vo.vod.CourseQueryVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
public interface CourseService extends IService<Course> {

    /**
     * 课程列表页面
     *
     * @param current       当前
     * @param size          大小
     * @param courseQueryVo 课程查询签证官
     * @return {@link PageDTO}<{@link Course}>
     */
    IPage<Course> coursePagesList(Long current, Long size, CourseQueryVo courseQueryVo);

    /**
     * 保存课程
     *
     * @param courseFormVo 课程形式签证官
     */
    Long saveCourse(CourseFormVo courseFormVo);

    /**
     * 课程信息回显
     *
     * @param id id
     * @return {@link CourseFormVo}
     */
    CourseFormVo getCourseFormVoById(Long id);

    /**
     * 更新课程通过id
     *
     * @param courseFormVo 课程形式签证官
     */
    Long updateCourseById(CourseFormVo courseFormVo);

    void deleteCourse(Long courseId);

    /**
     * 课程信息查询
     *
     * @param courseId 进程id
     * @return {@link CoursePublishVo}
     */
    CoursePublishVo courseInformationQueryInEnd(Long courseId);

    void courseFinalRelease(Long courseId);
}