package com.iktpreobuka.elektronskidnevnik1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.iktpreobuka.elektronskidnevnik1.entity")
public class ElektronskiDnevnik1Application {

	public static void main(String[] args) {
		SpringApplication.run(ElektronskiDnevnik1Application.class, args);
	}

}
