package com.example.springcloud.service;

import com.example.springcloud.entity.CommonResult;
import com.example.springcloud.entity.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    //能不能直接调Service呢 不会做
    //@GetMapping(value = "/payment/get/{id}")
    //CommonResult<Payment> getPaymentById(@Param("id") Long id);
}
