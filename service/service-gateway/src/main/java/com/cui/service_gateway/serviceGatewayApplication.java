package com.cui.service_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 崔令雨
 * {@code @date} 2022/7/17 16:48
 * {@code @Version} 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class serviceGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(serviceGatewayApplication.class);
    }
}