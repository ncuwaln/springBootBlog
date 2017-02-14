package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Administrator on 2017/2/5.
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@ServletComponentScan
@EnableJpaRepositories(basePackages="com.blog.repository")
@EntityScan(basePackages = {"com.blog.model"})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
