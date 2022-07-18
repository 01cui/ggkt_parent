package com.cui.ggkt.vod.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.ggkt.model.vod.Comment;
import com.cui.ggkt.vod.mapper.CommentMapper;
import com.cui.ggkt.vod.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Service
public class CommentServiceImp extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}