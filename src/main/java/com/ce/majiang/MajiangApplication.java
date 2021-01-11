package com.ce.majiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class MajiangApplication {

    public static void main(String[] args) {
        SpringApplication.run(MajiangApplication.class, args);
    }

}
