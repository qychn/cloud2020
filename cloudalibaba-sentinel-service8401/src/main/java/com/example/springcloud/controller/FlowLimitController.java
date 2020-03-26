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
}
