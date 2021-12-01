package com.ondrejkoula.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.ondrejkoula.repository")
@EntityScan("com.ondrejkoula.domain")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
