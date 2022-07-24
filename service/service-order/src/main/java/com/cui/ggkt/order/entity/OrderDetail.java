package com.cui.ggkt.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单明细 订单明细
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("order_detail")
@ApiModel(value = "OrderDetail对象", description = "订单明细 订单明细")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("课程id")
    @TableField("course_id")
    private Long courseId;

    @ApiModelProperty("课程名称")
    @TableField("course_name")
    private String courseName;

    @ApiModelProperty("课程封面")
    @TableField("cover")
    private String cover;

    @ApiModelProperty("订单编号")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("原始金额")
    @TableField("origin_amount")
    private BigDecimal originAmount;

    @ApiModelProperty("优惠劵减免金额")
    @TableField("coupon_reduce")
    private BigDecimal couponReduce;

    @ApiModelProperty("最终金额")
    @TableField("final_amount")
    private BigDecimal finalAmount;

    @ApiModelProperty("会话id 当前会话id 继承购物车中会话id")
    @TableField("session_id")
    private String sessionId;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
