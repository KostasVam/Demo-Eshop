package com.example.eworxapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpSession;

@SpringBootApplication
public class EworxAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EworxAppApplication.class, args);
	}
}
