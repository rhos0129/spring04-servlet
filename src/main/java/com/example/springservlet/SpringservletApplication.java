package com.example.springservlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // 서블릿 스캔 후 자동 등록 - @WebServlet
@SpringBootApplication
public class SpringservletApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringservletApplication.class, args);
	}

}
