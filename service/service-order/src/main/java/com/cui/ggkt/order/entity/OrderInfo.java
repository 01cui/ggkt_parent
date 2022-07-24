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
 * 订单表 订单表
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("order_info")
@ApiModel(value = "OrderInfo对象", description = "订单表 订单表")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @TableField("nick_name")
    private String nickName;

    @TableField("phone")
    private String phone;

    @ApiModelProperty("原始金额")
    @TableField("origin_amount")
    private BigDecimal originAmount;

    @ApiModelProperty("优惠券减免")
    @TableField("coupon_reduce")
    private BigDecimal couponReduce;

    @ApiModelProperty("最终金额")
    @TableField("final_amount")
    private BigDecimal finalAmount;

    @ApiModelProperty("订单状态")
    @TableField("order_status")
    private String orderStatus;

    @ApiModelProperty("订单交易编号（第三方支付用)")
    @TableField("out_trade_no")
    private String outTradeNo;

    @ApiModelProperty("订单描述(第三方支付用)")
    @TableField("trade_body")
    private String tradeBody;

    @ApiModelProperty("session id")
    @TableField("session_id")
    private String sessionId;

    @ApiModelProperty("地区id")
    @TableField("province")
    private String province;

    @ApiModelProperty("支付时间")
    @TableField("pay_time")
    private LocalDateTime payTime;

    @ApiModelProperty("失效时间")
    @TableField("expire_time")
    private LocalDateTime expireTime;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
