package day6.dawnoneline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DawnonelineApplication {

    public static void main(String[] args) {
        SpringApplication.run(DawnonelineApplication.class, args);
    }

}
