package com.example.dataJpa_relations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {


    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);  // Minimum active threads
        executor.setMaxPoolSize(200);  // Maximum threads allowed
        executor.setQueueCapacity(500); // Queue to hold requests before rejecting
        executor.setThreadNamePrefix("AsyncThread-");
        executor.initialize();
        return executor;
    }



}
