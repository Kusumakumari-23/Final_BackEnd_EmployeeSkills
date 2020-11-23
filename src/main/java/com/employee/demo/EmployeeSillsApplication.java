package com.employee.demo;

import org.apache.ibatis.type.MappedTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.employee.demo.entity.Employee;
import com.employee.demo.entity.Skill;

//@MappedTypes(Employee.class)
//@MapperScan("com.employee.demo.mybatis")
@SpringBootApplication
public class EmployeeSillsApplication {
	
	private static final Logger LOGGER = LogManager.getLogger(EmployeeSillsApplication.class);

	public static void main(String[] args) {
		
		SpringApplication.run(EmployeeSillsApplication.class, args);
		
		LOGGER.info("for informational purpose");
		LOGGER.warn("for warning purpose");
		LOGGER.error("for logging errors");
		LOGGER.trace("for tracing purpose");
		LOGGER.debug("for debugging purpose");
	}

}
