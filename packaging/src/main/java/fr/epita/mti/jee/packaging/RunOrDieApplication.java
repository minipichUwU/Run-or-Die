package fr.epita.mti.jee.packaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "fr.epita.mti.jee")
public class RunOrDieApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunOrDieApplication.class, args);
    }
}
