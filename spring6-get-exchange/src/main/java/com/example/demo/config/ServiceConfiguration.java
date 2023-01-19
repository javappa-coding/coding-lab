package com.example.demo.config;

import com.example.demo.service.CommentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ServiceConfiguration {

    @Bean
    CommentService commentService() {
        WebClient client = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(
                WebClientAdapter.forClient(client)).build();
        return factory.createClient(CommentService.class);
    }
}