package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.Preproccesingservice;

@SpringBootApplication
public class MarathiTextSummarizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarathiTextSummarizationApplication.class, args);
	}
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	@Bean
	public Preproccesingservice ps() {
		return new Preproccesingservice();
	}

}
