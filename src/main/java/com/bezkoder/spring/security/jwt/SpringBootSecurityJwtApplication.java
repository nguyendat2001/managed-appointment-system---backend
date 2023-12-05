package com.bezkoder.spring.security.jwt;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bezkoder.spring.security.jwt.security.services.fileService.FilesStorageService;

@SpringBootApplication
public class SpringBootSecurityJwtApplication {

//	@Resource
//	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(FilesStorageService storageService) {
		return (args) -> {
//			storageService.deleteAll();
			storageService.init();
		};
	}

}
