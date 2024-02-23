package xyz.strashi.ruestarmel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import xyz.strashi.ruestarmel.model.StorageProperties;
import xyz.strashi.ruestarmel.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class RuestarmelApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuestarmelApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
         storageService.init();
        };
    }

}
