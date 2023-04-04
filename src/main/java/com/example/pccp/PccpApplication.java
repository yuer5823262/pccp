package com.example.pccp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.example.pccp.model.dao")
public class PccpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PccpApplication.class, args);
    }

}
