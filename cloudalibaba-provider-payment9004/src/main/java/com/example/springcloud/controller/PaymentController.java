package com.example.springcloud.controller;

import com.example.springcloud.entity.CommonResult;
import com.example.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"dsv66dv87vd8vd68sdv90"));
        hashMap.put(2L,new Payment(2L,"8g7bb8fbdb7fg8nb9d9b9"));
        hashMap.put(3L,new Payment(3L,"d55av78fbasdv7fasd8vv"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable(value = "id") Long id){
        Payment payment = hashMap.get(id);
        return new CommonResult<>(200,"serverPort: "+serverPort,payment);
    }
}
