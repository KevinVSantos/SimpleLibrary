package br.com.KevinVSantos.SimpleLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SimpleLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleLibraryApplication.class, args);
	}

}
