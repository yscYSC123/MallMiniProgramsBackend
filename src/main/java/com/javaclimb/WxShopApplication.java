package com.javaclimb;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.javaclimb.mapper")
public class WxShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxShopApplication.class, args);
    }

}
