package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HotelRunner {

    public static void main(String[] args) {
        SpringApplication.run(HotelRunner.class, args);
    }
}
