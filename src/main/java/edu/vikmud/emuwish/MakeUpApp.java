package edu.vikmud.emuwish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan({"edu.vikmud.emuwish"})
@EntityScan("edu.vikmud.emuwish")
@RestController
public class DndudbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DndudbApplication.class, args);
	}


}
