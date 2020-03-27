package com.example.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.springcloud.entity.CommonResult;
import com.example.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")   //没有配置
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
                    exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable(value = "id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException 非法参数异常");
        }else if (result.getData()==null){
            throw new NullPointerException("NullPointerException 无内容");
        }
        return result;
    }
    //fallback
    public CommonResult handlerFallback(@PathVariable(value = "id") Long id,Throwable e){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底fallback异常，异常内容 "+ e.getMessage(),payment);
    }
    //blockhandler
    public CommonResult blockHandler(@PathVariable(value = "id") Long id, BlockException e){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"blockHandler限流，异常 "+ e.getMessage(),payment);
    }
}
