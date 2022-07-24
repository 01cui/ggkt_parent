package com.cui.ggkt.order.service.impl;

import com.cui.ggkt.order.entity.PaymentInfo;
import com.cui.ggkt.order.mapper.PaymentInfoMapper;
import com.cui.ggkt.order.service.PaymentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付信息表 服务实现类
 * </p>
 *
 * @author 崔令雨
 * @since 2022-07-24
 */
@Service
public class PaymentInfoServiceImp extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

}
