package com.dinotaurent.mscanciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EntityScan({"com.dinotaurent.commonsartistas.models.entity","com.dinotaurent.commonscancionesalbumes.models.entity"})
public class MsCancionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCancionesApplication.class, args);
	}

}
