package com.cui.ggkt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.cui.R.ResultCodeEnum;
import com.cui.ex.SiliconValleyClassExceptionHandl;
import com.cui.ggkt.model.vod.Subject;
import com.cui.ggkt.vo.vod.SubjectEeVo;
import com.cui.ggkt.vod.copy.SubjectMapping;
import com.cui.ggkt.vod.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 崔令雨
 * {@code @date} 2022/7/13 13:32
 * {@code @Version} 1.0
 */
@Component
@Slf4j
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {

    List<SubjectEeVo> subjectEeVos = new ArrayList<>();

    @Resource
    private SubjectMapping subjectMapping;

    @Resource
    private SubjectService subjectService;

    @Override
    public void invoke(SubjectEeVo data, AnalysisContext context) {
        log.info("开始读取表格数据");
        subjectEeVos.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<Subject> subjects = subjectMapping.toSubject(subjectEeVos);

        boolean b = subjectService.saveBatch(subjects);

        if (!b) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.COURSES_TO_ADD_FAILURE);
        }

        subjectEeVos.clear();
    }
}