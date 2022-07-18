package com.cui.ggkt.vod.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.R.Result;
import com.cui.ggkt.model.vod.Teacher;
import com.cui.ggkt.vo.vod.TeacherQueryVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 页面列表
     *
     * @param current        当前
     * @param size           大小
     * @param teacherQueryVo 老师查询签证官
     * @return {@link Result}<{@link IPage}<{@link Teacher}>>
     */
    Result<IPage<Teacher>> pageList(Long current, Long size, TeacherQueryVo teacherQueryVo);

    String pictureUpload(MultipartFile file);
}