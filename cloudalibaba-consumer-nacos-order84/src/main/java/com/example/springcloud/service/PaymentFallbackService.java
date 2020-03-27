package com.example.springcloud.service;

import com.example.springcloud.entity.CommonResult;
import com.example.springcloud.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444,"服务降级返回---PaymentFallbackService",new Payment(id,"errorserial"));
    }
}
