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
 * 支付信息表
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("payment_info")
@ApiModel(value = "PaymentInfo对象", description = "支付信息表")
public class PaymentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("对外业务编号")
    @TableField("out_trade_no")
    private String outTradeNo;

    @ApiModelProperty("订单编号")
    @TableField("order_id")
    private Long orderId;

    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("支付宝交易编号")
    @TableField("alipay_trade_no")
    private String alipayTradeNo;

    @ApiModelProperty("支付金额")
    @TableField("total_amount")
    private BigDecimal totalAmount;

    @ApiModelProperty("交易内容")
    @TableField("trade_body")
    private String tradeBody;

    @TableField("payment_type")
    private String paymentType;

    @ApiModelProperty("支付状态")
    @TableField("payment_status")
    private String paymentStatus;

    @ApiModelProperty("回调信息")
    @TableField("callback_content")
    private String callbackContent;

    @ApiModelProperty("回调时间")
    @TableField("callback_time")
    private LocalDateTime callbackTime;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
