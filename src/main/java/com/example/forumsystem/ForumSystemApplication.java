package com.example.forumsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ForumSystemApplication {
    // 管理生命周期的一种方法
    @PostConstruct
    public void init(){
        // 解决netty启动冲突问题
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    public static void main(String[] args) {
        SpringApplication.run(ForumSystemApplication.class, args);
    }

}
