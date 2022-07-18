package com.cui.ggkt.vod.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cui.ggkt.model.vod.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-02
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}