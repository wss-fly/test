package com.feitui.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.feitui")
@MapperScan({"com.feitui.admin.mapper", "com.feitui.mapper"})
public class FeiTuiAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeiTuiAdminApplication.class, args);
    }
}