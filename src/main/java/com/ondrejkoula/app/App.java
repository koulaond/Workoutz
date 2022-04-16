package com.ondrejkoula.app;

import com.ondrejkoula.repository.IncorporatedItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(value = "com.ondrejkoula.repository", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {IncorporatedItemRepository.class})
})
@EntityScan("com.ondrejkoula.domain")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
