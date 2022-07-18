package com.cui.ggkt.vod.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.R.Result;
import com.cui.ggkt.model.vod.Teacher;
import com.cui.ggkt.vo.vod.TeacherQueryVo;
import com.cui.ggkt.vod.mapper.TeacherMapper;
import com.cui.ggkt.vod.service.TeacherService;
import com.cui.ggkt.vod.utils.OssUtils;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Service
@Slf4j
public class TeacherServiceImp extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    private final TeacherMapper teacherMapper;

    public TeacherServiceImp(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Result<IPage<Teacher>> pageList(Long current, Long size, TeacherQueryVo teacherQueryVo) {

        IPage<Teacher> teacherIPage = new PageDTO<>(current, size);
        if (Objects.isNull(teacherQueryVo)) {
            teacherMapper.selectPage(teacherIPage, null);

            return Result.ok(teacherIPage);
        }

        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();

        teacherQueryWrapper.eq(StringUtils.hasText(teacherQueryVo.getName()),
                        "name",
                        teacherQueryVo.getName())
                .eq(!Objects.isNull(teacherQueryVo.getLevel()),
                        "level",
                        teacherQueryVo.getLevel())
                .ge(StringUtils.hasText(teacherQueryVo.getJoinDateBegin()),
                        "join_date",
                        teacherQueryVo.getJoinDateBegin())
                .le(StringUtils.hasText(teacherQueryVo.getJoinDateEnd()),
                        "join_date",
                        teacherQueryVo.getJoinDateEnd());

        teacherMapper.selectPage(teacherIPage, teacherQueryWrapper);
        log.info("查询到的总记录数:{}", teacherIPage.getTotal());

        return Result.ok(teacherIPage);

    }

    @Override
    public String pictureUpload(MultipartFile file) {

        try {

            // 使用高级接口必须先保证本进程存在一个 TransferManager 实例，如果没有则创建
// 详细代码参见本页：高级接口 -> 创建 TransferManager
            TransferManager transferManager = OssUtils.createTransferManager();

// 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
            String bucketName = "cui123-1311851110";
// 对象键(Key)是对象在存储桶中的唯一标识。
            String originalFilename = file.getOriginalFilename();

            int i = Objects.requireNonNull(originalFilename).indexOf(".");
            String substring = Objects.requireNonNull(file.getOriginalFilename()).substring(i);
            String key = RandomUtil.randomString(10) + substring;
            String today = DateUtil.today();
            key = today + "/" + key;


// 这里创建一个 ByteArrayInputStream 来作为示例，实际中这里应该是您要上传的 InputStream 类型的流
            InputStream inputStream = file.getInputStream();

            ObjectMetadata objectMetadata = new ObjectMetadata();
// 上传的流如果能够获取准确的流长度，则推荐一定填写 content-length
// 如果确实没办法获取到，则下面这行可以省略，但同时高级接口也没办法使用分块上传了
            int available = inputStream.available();
            objectMetadata.setContentLength(available);

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);

            // 高级接口会返回一个异步结果Upload
            // 可同步地调用 waitForUploadResult 方法等待上传完成，成功返回UploadResult, 失败抛出异常
            Upload upload = transferManager.upload(putObjectRequest);
            UploadResult uploadResult = upload.waitForUploadResult();
            // 确定本进程不再使用 transferManager 实例之后，关闭之
            // 详细代码参见本页：高级接口 -> 关闭 TransferManager
            OssUtils.shutdownTransferManager(transferManager);
            return "https://" + bucketName + ".cos.ap-beijing.myqcloud.com/" + key;
        } catch (CosClientException | InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}