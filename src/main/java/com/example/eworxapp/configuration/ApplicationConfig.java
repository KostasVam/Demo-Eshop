package com.example.eworxapp.configuration;

import com.example.eworxapp.repository.ItemRepositoryImpl;
import com.example.eworxapp.service.ItemServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ItemServiceImpl itemService() {
        return new ItemServiceImpl();
    }

    @Bean
    public ItemRepositoryImpl itemRepository() {
        return new ItemRepositoryImpl();
    }
}
