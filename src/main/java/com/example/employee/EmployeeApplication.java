package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}

@RestController
@RequestMapping("/")
class GreetingsController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping
	public String greetings() {
		return "Hello!, from employee application.";
	}

	@GetMapping("/allservices")
	public List<String> getAllClients() {
		return discoveryClient.getServices();
	}

}