package com.wzy.demo.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.zxp.esclientrhl.annotation.EnableESTools;

/**
 * @author EjyUcsApplication
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"com.wzy"})
@MapperScan("com.wzy.demo.service.dao")
@EnableWebMvc
@EnableESTools(basePackages = {"com.wzy.demo.service.repository"}, entityPath = {"com.wzy.demo.service.pojo.es"})
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
