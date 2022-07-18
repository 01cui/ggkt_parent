package com.cui.ggkt.vod.service.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.R.ResultCodeEnum;
import com.cui.ex.SiliconValleyClassExceptionHandl;
import com.cui.ggkt.model.vod.Chapter;
import com.cui.ggkt.model.vod.Course;
import com.cui.ggkt.model.vod.CourseDescription;
import com.cui.ggkt.model.vod.Subject;
import com.cui.ggkt.model.vod.Teacher;
import com.cui.ggkt.vo.vod.CourseFormVo;
import com.cui.ggkt.vo.vod.CoursePublishVo;
import com.cui.ggkt.vo.vod.CourseQueryVo;
import com.cui.ggkt.vod.copy.CourseMapping;
import com.cui.ggkt.vod.mapper.CourseMapper;
import com.cui.ggkt.vod.service.ChapterService;
import com.cui.ggkt.vod.service.CourseService;
import com.cui.ggkt.vod.service.DescriptionService;
import com.cui.ggkt.vod.service.SubjectService;
import com.cui.ggkt.vod.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Service
public class CourseServiceImp extends ServiceImpl<CourseMapper, Course> implements CourseService {

    private final TeacherService teacherService;
    private final SubjectService subjectService;

    private final CourseMapping courseMapping;
    private final DescriptionService descriptionService;

    private final ChapterService chapterService;

    public CourseServiceImp(TeacherService teacherService, SubjectService subjectService, CourseMapping courseMapping, DescriptionService descriptionService, ChapterService chapterService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.courseMapping = courseMapping;
        this.descriptionService = descriptionService;
        this.chapterService = chapterService;
    }

    @Override
    public IPage<Course> coursePagesList(Long current, Long size, CourseQueryVo courseQueryVo) {

        IPage<Course> courseIPage = new Page<>(current, size);

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(courseQueryVo.getTeacherId() != null,
                "teacher_id",
                courseQueryVo.getTeacherId());

        queryWrapper.eq(courseQueryVo.getSubjectId() != null,
                "subject_id",
                courseQueryVo.getSubjectId());
        queryWrapper.eq(courseQueryVo.getSubjectParentId() != null,
                "subject_parent_id",
                courseQueryVo.getSubjectParentId());
        queryWrapper.eq(StringUtils.hasLength(courseQueryVo.getTitle()),
                "title",
                courseQueryVo.getTitle());

        IPage<Course> courseIPage1 = baseMapper.selectPage(courseIPage, queryWrapper);
        List<Course> records = courseIPage1.getRecords();
        records.forEach(this::fillingParameters);

        return courseIPage1;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long saveCourse(CourseFormVo courseFormVo) {

        Course course = courseMapping.toCourse(courseFormVo);

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());

        int insert = baseMapper.insert(course);
        if (insert != 1) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.COURSES_TO_ADD_FAILURE);
        }
        courseDescription.setCourseId(course.getId());
        boolean save = descriptionService.save(courseDescription);
        if (!save) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.COURSES_TO_ADD_FAILURE);
        }
        return course.getId();
    }

    @Override
    public CourseFormVo getCourseFormVoById(Long id) {

        Course course = baseMapper.selectById(id);

        CourseFormVo courseFormVo = courseMapping.toCourseFormVo(course);

        QueryWrapper<CourseDescription> courseDescriptionQueryWrapper = new QueryWrapper<>();

        courseDescriptionQueryWrapper.eq("course_id", course.getId());

        CourseDescription courseDescription = descriptionService.getOne(courseDescriptionQueryWrapper);
        courseFormVo.setDescription(courseDescription.getDescription());
        return courseFormVo;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long updateCourseById(CourseFormVo courseFormVo) {

        Course course = courseMapping.toCourse(courseFormVo);

        int i = baseMapper.updateById(course);

        if (i != 1) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.COURSE_UPDATE_FAIL);
        }
        CourseDescription courseDescription = new CourseDescription();

        courseDescription.setDescription(courseFormVo.getDescription());
        QueryWrapper<CourseDescription> courseDescriptionQueryWrapper = new QueryWrapper<>();
        courseDescriptionQueryWrapper.eq("course_id", course.getId());
        boolean save = descriptionService.update(courseDescription, courseDescriptionQueryWrapper);

        if (!save) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.COURSE_UPDATE_FAIL);
        }
        return course.getId();
    }

    @Override
    public void deleteCourse(Long courseId) {

        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<Chapter> list = chapterService.list(chapterQueryWrapper);

        if (!list.isEmpty()) {
            list.forEach(item -> {
                chapterService.deleteChapter(item.getId());
            });
        }
        int i = baseMapper.deleteById(courseId);
        if (i != 1) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.DELETE_COURSES_FAIL);
        }
    }

    @Override
    public CoursePublishVo courseInformationQueryInEnd(Long courseId) {
        return baseMapper.courseInformationQueryInEnd(courseId);
    }

    @Override
    public void courseFinalRelease(Long courseId) {
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(1);
        course.setUpdateTime(DateUtil.date());

        int i = baseMapper.updateById(course);
        if (1 != 1) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.COURSE_RELEASE_FAILURE);
        }
    }

    private void fillingParameters(Course item) {
        Teacher teacher = teacherService.getById(item.getTeacherId());
        item.getParam().put("teacherName", teacher.getName());

        Subject subject = subjectService.getById(item.getSubjectId());

        item.getParam().put("subjectName", subject.getTitle());

        Subject subjectParentId = subjectService.getById(item.getSubjectParentId());

        item.getParam().put("subjectParentId", subjectParentId.getTitle());

    }
}