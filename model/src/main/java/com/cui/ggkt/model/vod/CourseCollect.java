package com.cui.ggkt.model.vod;

import com.cui.ggkt.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "CourseCollect")
@TableName("course_collect")
public class CourseCollect extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "课程讲师ID")
	@TableField("course_id")
	private Long courseId;

	@ApiModelProperty(value = "会员ID")
	@TableField("user_id")
	private Long userId;

}