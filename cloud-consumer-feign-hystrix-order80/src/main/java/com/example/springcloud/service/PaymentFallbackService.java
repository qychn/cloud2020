package com.example.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 重建一个类实现PaymentHystrixService 统一为接口里的方法进行异常处理
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "---------PaymentFallbackService fallback paymentInfo_OK , /(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "---------PaymentFallbackService fallback paymentInfo_TimeOut , /(ㄒoㄒ)/~~";
    }
}
