package com.bhctobacco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication(scanBasePackages={"com.bhctobacco.batch", "com.bhctobacco.listener", "com.bhctobacco.dto", "com.bhctobacco.rest", "com.bhctobacco.repository", "com.bhctobacco.model", "com.bhctobacco.enums", "com.bhctobacco.config", "com.bhctobacco.service"})
public class BacTobacco {

    public static void main(String[] args) {
        SpringApplication.run(BacTobacco.class, args);
    }

}
