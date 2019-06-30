package com.example.weibohuati;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:/applicationContext.xml"})
@MapperScan("com.example.weibohuati.mapper")
public class WeiboserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeiboserviceApplication.class, args);
    }

}
