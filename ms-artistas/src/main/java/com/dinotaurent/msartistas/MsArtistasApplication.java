package com.dinotaurent.msartistas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
@EntityScan({"com.dinotaurent.commonsartistasgeneros.models.entity","com.dinotaurent.commonscancionesalbumes.models.entity"})
public class MsArtistasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsArtistasApplication.class, args);
	}

}
