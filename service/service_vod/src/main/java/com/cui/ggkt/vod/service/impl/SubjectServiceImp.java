package com.cui.ggkt.vod.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.ggkt.model.vod.Subject;
import com.cui.ggkt.utils.ConverterUtil;
import com.cui.ggkt.vo.vod.SubjectEeVo;
import com.cui.ggkt.vod.mapper.SubjectMapper;
import com.cui.ggkt.vod.service.SubjectService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Service
public class SubjectServiceImp extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {


    @Override
    public List<Subject> getSubject(Long id) {

        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("parent_id", id);
        List<Subject> subjects = baseMapper.selectList(queryWrapper);

        if (CollUtil.isNotEmpty(subjects)) {
            ifThereIsAChildNode(subjects);
        }
        return subjects;
    }

    @Override
    public void courseClassificationDerived(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        try {
            String fileName = URLEncoder.encode("课程分类", "utf-8");
            response.setHeader("content-disposition", "attachment;filename" + fileName + ".xlsx");
            List<Subject> subjects = baseMapper.selectList(null);

            List<Subject> subjectsVo = ConverterUtil.convertList(subjects, SubjectEeVo.class);

            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("课程分类")
                    .doWrite(subjectsVo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ifThereIsAChildNode(List<Subject> subjects) {
        for (Subject subject : subjects) {
            Long id = subject.getId();
            QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", id);
            Long aLong = baseMapper.selectCount(queryWrapper);
            subject.setHasChildren(aLong > 0);
        }
    }
}