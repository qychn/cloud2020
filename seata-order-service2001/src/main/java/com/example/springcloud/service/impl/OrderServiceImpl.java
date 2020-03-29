package com.example.springcloud.service.impl;

import com.example.springcloud.dao.OrderDao;
import com.example.springcloud.entity.Order;
import com.example.springcloud.service.AccountService;
import com.example.springcloud.service.OrderService;
import com.example.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @Override
    @GlobalTransactional(name = "qy_create_order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("---->开始新建订单");
        orderDao.create(order);

        log.info("---->开始调用库存做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());

        log.info("---->开始调用账号扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());

        log.info("---->修改订单状态到1 已完成");
        orderDao.update(order.getUserId(),0);
        log.info("---->结束");
    }
}
