package com.example.springcloud.service.impl;

import com.example.springcloud.dao.AccountDao;
import com.example.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;
    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("---->AccountService扣减余额开始");
        accountDao.decrease(userId, money);
        log.info("---->AccountService扣减余额结束");
    }
}
