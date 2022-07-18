package com.cui.ggkt.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 崔令雨
 * {@code @date} 2022/7/18 8:16
 * {@code @Version} 1.0
 */
@SpringBootApplication
@ComponentScan("com.cui")
@EnableDiscoveryClient
@EnableTransactionManagement

public class serviceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(serviceOrderApplication.class);
    }
}