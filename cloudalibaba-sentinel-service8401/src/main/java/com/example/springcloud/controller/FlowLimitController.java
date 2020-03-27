package com.example.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        //暂停毫秒
        //try {TimeUnit.MILLISECONDS.sleep(1000);}catch (InterruptedException e){e.printStackTrace();}
        return "---------A";
    }

    @GetMapping("/testB")
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

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "dealtestHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "---------testHotKey";
    }
    public String dealtestHotKey(String p1, String p2, BlockException e) {
        return "--------兜底降级方法deal_testHotKey";
    }
}
