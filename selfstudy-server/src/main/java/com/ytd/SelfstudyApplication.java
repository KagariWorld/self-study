package com.ytd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
public class SelfstudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SelfstudyApplication.class,args);
        log.info("server started...");
    }
}
