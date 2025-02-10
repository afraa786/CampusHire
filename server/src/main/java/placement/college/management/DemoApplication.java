package placement.college.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @SpringBootApplication(scanBasePackages = "placement.college.management")
@SpringBootApplication
@EnableJpaRepositories("placement.college.management")  // Ensure correct package
@EntityScan("placement.college.management") 
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
