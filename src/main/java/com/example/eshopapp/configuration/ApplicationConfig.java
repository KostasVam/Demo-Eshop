package com.example.eshopapp.configuration;

import com.example.eshopapp.repository.ItemRepositoryImpl;
import com.example.eshopapp.service.ItemServiceImpl;
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
