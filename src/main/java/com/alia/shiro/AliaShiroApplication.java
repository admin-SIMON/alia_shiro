package com.alia.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Simon
 * @date 17:02 2019/7/25
 **/
@SpringBootApplication
@MapperScan("com.alia.shiro.dao")
public class AliaShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliaShiroApplication.class, args);
    }

}
