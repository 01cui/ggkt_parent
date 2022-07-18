package com.cui.ggkt.vod.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.R.ResultCodeEnum;
import com.cui.ex.SiliconValleyClassExceptionHandl;
import com.cui.ggkt.model.vod.Video;
import com.cui.ggkt.vo.vod.VideoVo;
import com.cui.ggkt.vod.copy.VideoMapping;
import com.cui.ggkt.vod.mapper.VideoMapper;
import com.cui.ggkt.vod.service.VideoService;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Service
@Slf4j
public class VideoServiceImp extends ServiceImpl<VideoMapper, Video> implements VideoService {

    private final VideoMapping videoMapping;

    public VideoServiceImp(VideoMapping videoMapping) {
        this.videoMapping = videoMapping;
    }

    @Override
    public List<VideoVo> getVideoList(Long chapterId) {
        LambdaQueryWrapper<Video> videoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        videoLambdaQueryWrapper.eq(Video::getChapterId, chapterId);
        List<Video> videos = baseMapper.selectList(videoLambdaQueryWrapper);
        return videoMapping.toVideoList(videos);
    }

    @Override
    public void saveVideo(Video video) {
        int insert = baseMapper.insert(video);
        log.info("需要添加的数据:{}", video);
        if (insert != 1) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.ADD_FAILURE_SECTION);
        }
    }

    @Override
    public String uploadVideo() {

        VodUploadClient client = new VodUploadClient("AKID1sXx8HF0Wn9IFxQgjX4OVO1PYVEl8IE9",
                "jEWZOzNXS8vVnrgIiwxkJfmfJyPbaMvF");
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath("D://谷粒学院//项目资料//1-阿里云上传测试视频//6 - What If I Want to Move Faster.mp4");
        request.setProcedure("LongVideoPreset");
        String fileId;
        try {
            VodUploadResponse response = client.upload("ap-guangzhou", request);
            fileId = response.getFileId();
        } catch (Exception e) {
            throw new SiliconValleyClassExceptionHandl(ResultCodeEnum.VIDEO_UPLOAD_FAILS);
        }
        return fileId;
    }

    @Override
    public boolean deleteVideo(Long id) {
        Video video = baseMapper.selectById(id);

        Optional.ofNullable(video)
                .ifPresent(item -> {
                    log.info("需要删除的视频id:{}", video.getVideoSourceId());
                    if (item.getVideoSourceId() != null) {
                        String videoSourceId = item.getVideoSourceId();
                        Credential credential = new Credential("AKID1sXx8HF0Wn9IFxQgjX4OVO1PYVEl8IE9",
                                "jEWZOzNXS8vVnrgIiwxkJfmfJyPbaMvF");
                        DeleteMediaRequest request = new DeleteMediaRequest();
                        request.setFileId(videoSourceId);
                        VodClient vodClient = new VodClient(credential, "");
                        try {
                            DeleteMediaResponse response = vodClient.DeleteMedia(request);
                            log.info(response.getRequestId());
                        } catch (TencentCloudSDKException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        baseMapper.deleteById(id);
        return true;
    }
}