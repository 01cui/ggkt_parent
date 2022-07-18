package com.cui.ggkt.vod.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cui.R.Result;
import com.cui.ggkt.model.vod.Course;
import com.cui.ggkt.vo.vod.CourseFormVo;
import com.cui.ggkt.vo.vod.CoursePublishVo;
import com.cui.ggkt.vo.vod.CourseQueryVo;
import com.cui.ggkt.vod.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@RestController
@RequestMapping("/vod/course")
@Api("课程")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("coursePagesList/{current}/{size}")
    public Result<IPage<Course>> coursePagesList(@PathVariable Long current,
                                                 @PathVariable Long size,
                                                 @RequestBody CourseQueryVo courseQueryVo) {
        return Result.ok(courseService.coursePagesList(current, size, courseQueryVo));
    }

    @PostMapping("saveCourse")
    public Result<Long> saveCourse(@RequestBody CourseFormVo courseFormVo) {
        return Result.ok(courseService.saveCourse(courseFormVo));
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result<CourseFormVo> get(@PathVariable Long id) {
        CourseFormVo course = courseService.getCourseFormVoById(id);
        return Result.ok(course);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result<Long> updateById(@RequestBody CourseFormVo courseFormVo) {
        return Result.ok(courseService.updateCourseById(courseFormVo));
    }

    @DeleteMapping("deleteCourse/{courseId}")
    public Result<Void> deleteCourse(@PathVariable Long courseId) {

        courseService.deleteCourse(courseId);
        return Result.ok();
    }

    @GetMapping("courseInformationQueryInEnd/{courseId}")
    public Result<CoursePublishVo> courseInformationQueryInEnd(@PathVariable Long courseId) {
        return Result.ok(courseService.courseInformationQueryInEnd(courseId));
    }

    @PostMapping("courseFinalRelease/{courseId}")
    public Result<Void> courseFinalRelease(@PathVariable Long courseId) {
        courseService.courseFinalRelease(courseId);
        return Result.ok();
    }


}