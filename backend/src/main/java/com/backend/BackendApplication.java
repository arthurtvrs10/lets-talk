package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@SpringBootApplication
public class BackendApplication {

	@GetMapping("/api/status")
	public Map<String, String> status(){
		return Map.of("status", "online");
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
