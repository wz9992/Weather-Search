package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class WeatherApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }
}
