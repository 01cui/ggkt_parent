package com.cui.ex;

import com.cui.R.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 崔令雨
 * {@code @date} 2022/7/3 11:43
 * {@code @Version} 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SiliconValleyClassExceptionHandl extends RuntimeException {


    private Integer code;

    private String message;

    public SiliconValleyClassExceptionHandl(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

}