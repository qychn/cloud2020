package com.example.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("testA")
    public String testA(){
        //暂停毫秒
        //try {TimeUnit.MILLISECONDS.sleep(1000);}catch (InterruptedException e){e.printStackTrace();}
        return "---------A";
    }

    @GetMapping("testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"\t"+"------testB");
        return "---------B";
    }

    @GetMapping("/testD")
    public String testD(){
        //暂停秒
        try {TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e){e.printStackTrace();}
        log.info("testD 测试RT 平均响应时间");
        return "------testD";
    }
    @GetMapping("/testE")
    public String testE(){
        log.info("testD 测试异常比例");
        int age=10/0;
        return "------testE";
    }
}
