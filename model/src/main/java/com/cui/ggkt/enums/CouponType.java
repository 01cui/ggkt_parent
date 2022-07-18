package com.cui.ggkt.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum CouponType {
    REGISTER(1,"注册"),
    RECOMMEND(2,"推荐购买");

    @EnumValue
    private final Integer code;
    private final String comment ;

    CouponType(Integer code, String comment ){
        this.code=code;
        this.comment=comment;
    }
}