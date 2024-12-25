package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

//호출 순서대로 파악하기
//@ComponentScan을 mapper 패키지로 하면
//컨트롤러 호출 불가
@SpringBootApplication
//@ComponentScan("com.example.demo")
public class WebStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebStudyApplication.class, args);
	}

}