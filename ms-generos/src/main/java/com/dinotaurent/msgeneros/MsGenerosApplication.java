package com.dinotaurent.msgeneros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan({"com.dinotaurent.commonsartistasgeneros.models.entity","com.dinotaurent.commonscancionesalbumes.models.entity"})
public class MsGenerosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGenerosApplication.class, args);
	}

}
