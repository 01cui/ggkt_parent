package com.cui.ggkt.model.vod;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cui.ggkt.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Teacher")
@TableName("teacher")
public class Teacher extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "讲师姓名")
	@TableField("name")
	@NotBlank(message = "讲师姓名不能为空")
	private String name;

	@ApiModelProperty(value = "讲师简介")
	@TableField("intro")
	@NotBlank(message = "讲师简介不能为空")
	private String intro;

	@ApiModelProperty(value = "讲师资历,一句话说明讲师")
	@TableField("career")
	@NotBlank(message = "讲师资历不能为空")
	private String career;

	@ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
	@TableField("level")
	@Min(value = 1,message = "头衔在[1,2]")
	@Max(2)
	private Integer level;

	@ApiModelProperty(value = "讲师头像")
	@TableField("avatar")
	@NotBlank(message = "讲师头像地址不能为空")
	private String avatar;

	@ApiModelProperty(value = "排序")
	@TableField("sort")
	@Min(value = 0,message = "排序字段值不能小于0")
	private Integer sort;

	@ApiModelProperty(value = "入驻时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("join_date")
	private Date joinDate;

}