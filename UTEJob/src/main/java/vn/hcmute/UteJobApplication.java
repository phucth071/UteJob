package vn.hcmute;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import vn.hcmute.config.StorageProperties;
import vn.hcmute.services.IStorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class) //thêm cấu hình storage
public class UteJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(UteJobApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(IStorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}

}
