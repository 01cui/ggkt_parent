package com.cui.ggkt.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.ggkt.model.vod.Subject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 获取课程分类
     *
     * @return {@link List}<{@link Subject}>
     * @param id
     */
    List<Subject> getSubject(Long id);

    /**
     * 课程分类导出
     *
     * @param response 响应
     */
    void courseClassificationDerived(HttpServletResponse response);
}