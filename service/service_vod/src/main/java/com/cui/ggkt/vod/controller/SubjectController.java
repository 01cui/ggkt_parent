package com.cui.ggkt.vod.controller;

import com.alibaba.excel.EasyExcel;
import com.cui.R.Result;
import com.cui.R.ResultCodeEnum;
import com.cui.ex.SiliconValleyClassExceptionHandl;
import com.cui.ggkt.model.vod.Subject;
import com.cui.ggkt.vo.vod.SubjectEeVo;
import com.cui.ggkt.vod.listener.SubjectListener;
import com.cui.ggkt.vod.service.SubjectService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@RestController
@RequestMapping("/vod/subject")
@Api("课程分类")
@Slf4j
public class SubjectController {

    private final SubjectService subjectService;

    private final SubjectListener subjectListener;

    public SubjectController(SubjectService subjectService, SubjectListener subjectListener) {
        this.subjectService = subjectService;
        this.subjectListener = subjectListener;
    }

    @GetMapping("getSubject/{id}")
    public Result<List<Subject>> getSubject(@PathVariable Long id) {
        return Result.ok(subjectService.getSubject(id));
    }

    @GetMapping("export")
    public void courseClassificationDerived(HttpServletResponse response) {
        subjectService.courseClassificationDerived(response);
    }

    @PostMapping("courseImport")
    public Result<Void> courseImport(@RequestPart MultipartFile file) {

        try {
            EasyExcel.read(file.getInputStream(), SubjectEeVo.class, subjectListener).sheet().doRead();
            log.info("读取完毕");
        } catch (IOException e) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.COURSES_TO_ADD_FAILURE);
        }
        return Result.ok();
    }


}