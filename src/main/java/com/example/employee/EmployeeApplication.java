package com.example.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
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

	@Autowired
	private DepartmentFeign departmentFeign;

	private Logger logger = LoggerFactory.getLogger(GreetingsController.class);

	@GetMapping
	public String greetings() {
		logger.info("Hello");
		return "Hello!, from employee application.";
	}

	@GetMapping("/allservices")
	public List<String> getAllClients() {
		logger.info("all services");
		return discoveryClient.getServices();
	}

	@GetMapping("/feign")
	public String getDepartmentName() {
		logger.info("feign");
		return this.departmentFeign.getAppName();
	}

}

@FeignClient("department")
interface DepartmentFeign {

	@GetMapping("/department/app/name")
	String getAppName();

}