package com.example.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.springcloud.entity.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult(444,e.getClass().getCanonicalName()+"\t 按客户自定义---1  服务不可用");
    }
    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(444,e.getClass().getCanonicalName()+"\t 按客户自定义---2  服务不可用");
    }
}
