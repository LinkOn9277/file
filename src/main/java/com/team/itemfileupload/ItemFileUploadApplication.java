package com.team.itemfileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 감사 활성화
public class ItemFileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemFileUploadApplication.class, args);
    }

}
