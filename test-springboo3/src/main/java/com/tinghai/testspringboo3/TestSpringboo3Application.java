package com.tinghai.testspringboo3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.tinghai.testspringboo3.dao"})
public class TestSpringboo3Application {

	public static void main(String[] args) {
		SpringApplication.run(TestSpringboo3Application.class, args);
	}

}
