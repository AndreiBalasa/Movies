package ro.qual.movieRentals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("ro.qual.movieRentals.ui")
public class AppConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
