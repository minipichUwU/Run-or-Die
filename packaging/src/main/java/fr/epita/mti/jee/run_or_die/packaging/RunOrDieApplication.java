package fr.epita.mti.jee.run_or_die.packaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "fr.epita.mti.jee.run_or_die")
public class RunOrDieApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunOrDieApplication.class, args);
    }
}
