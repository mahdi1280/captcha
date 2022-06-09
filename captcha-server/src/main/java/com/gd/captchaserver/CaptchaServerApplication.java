package com.gd.captchaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableFeignClients({"com.gd.captchaclient.client"})
//@EnableJpaRepositories({"com.gd.captchaserver.repository"})
//@ComponentScan({"com.gd.captchaclient", "com.gd.captchaserver","com.gd.captchaserver.controller", "com.gd.captchaserver.service", "com.gd.captchaclient.client"})
@ComponentScan({"com.gd"})
@EnableJpaRepositories({"com.gd"})
@EnableFeignClients({"com.gd"})
public class CaptchaServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(CaptchaServerApplication.class, args);
    }

}
