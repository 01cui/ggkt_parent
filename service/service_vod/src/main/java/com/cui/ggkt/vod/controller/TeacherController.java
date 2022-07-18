package com.cui.ggkt.vod.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cui.R.Result;
import com.cui.ggkt.model.vod.Teacher;
import com.cui.ggkt.vo.vod.TeacherQueryVo;
import com.cui.ggkt.vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@RestController
@RequestMapping("/vod/teacher")
@Api("讲师控制器")
@Slf4j
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 得到老师名单
     *
     * @return {@link Result}<{@link List}<{@link Teacher}>>
     */
    @GetMapping("getTeacherList")
    @ApiOperation("所讲讲师名单")
    public Result<List<Teacher>> getTeacherList() {
        return Result.ok(teacherService.list());
    }

    /**
     * 删除老师
     *
     * @param id id
     * @return {@link Result}<{@link Void}>
     */
    @ApiOperation("根据id删除讲师")
    @DeleteMapping("deleteTeacher/{id}")
    public Result<Void> deleteTeacher(
            @ApiParam(value = "id", defaultValue = "讲师id")
            @PathVariable("id") String id) {
        boolean b = teacherService.removeById(id);
        if (!b) {
            return Result.fail();
        }
        return Result.ok();
    }

    @PostMapping("pageTeacher/{current}/{size}")
    public Result<IPage<Teacher>> pagingQueryLecturer(
            @PathVariable("current") Long current,
            @PathVariable("size") Long size,
            @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        return teacherService.pageList(current, size, teacherQueryVo);
    }

    /**
     * 更新教师
     *
     * @return {@link Result}<{@link Void}>
     */
    @PutMapping("updateTeacher")
    @ApiOperation("更新讲师")
    public Result<Void> updateTeacher(
            @ApiParam(value = "讲师信息", defaultValue = "需要添加的讲师信息")
            @RequestBody Teacher teacher) {
        boolean b = teacherService.updateById(teacher);
        if (!b) {
            return Result.fail();
        }
        return Result.ok();
    }

    @PostMapping("saveTeacher")
    @ApiOperation("添加讲师")
    public Result<Void> saveTeacher(
            @Validated
            @ApiParam(value = "讲师信息", defaultValue = "需要修改的讲师信息")
            @RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (!save) {
            return Result.fail();
        }
        return Result.ok();
    }

    @DeleteMapping("deleteBatchTeacher")
    public Result<Void> deleteBatchTeacher(@RequestBody List<Long> idS) {
        log.info("开始批量删除");
        if (!idS.isEmpty()) {
            boolean b = teacherService.removeBatchByIds(idS);
            if (!b) {
                return Result.fail();
            }
        }
        return Result.ok();
    }

    @GetMapping("getOneTeacher/{teacherId}")
    public Result<Teacher> getOneTeacher(@PathVariable Long teacherId) {
        return Result.ok(teacherService.getById(teacherId));
    }

    @PostMapping("pictureUpload")
    public Result<String> pictureUpload(@RequestParam("file") @RequestPart MultipartFile file) {
        return Result.ok(teacherService.pictureUpload(file));
    }
}