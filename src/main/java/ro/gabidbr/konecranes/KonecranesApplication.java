package ro.gabidbr.konecranes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KonecranesApplication {

	public static void main(String[] args) {
		SpringApplication.run(KonecranesApplication.class, args);
	}

}
