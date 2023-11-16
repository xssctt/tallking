package com.example.tallking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.tallking.mapper")
@SpringBootApplication
public class TallkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TallkingApplication.class, args);
    }

}
