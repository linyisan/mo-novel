package com.heng;

import org.mapstruct.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NovelAdminApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(NovelAdminApplication.class);
    }
}
