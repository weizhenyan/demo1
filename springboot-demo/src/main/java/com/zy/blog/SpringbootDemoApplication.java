package com.zy.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * SpringbootDemoApplication
 *
 * @author 飞燕
 * @date   2022/8/8 13:02
 */
@SpringBootApplication
@EnableTransactionManagement
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}
