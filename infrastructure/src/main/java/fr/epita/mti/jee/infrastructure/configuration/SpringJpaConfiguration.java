package fr.epita.mti.jee.infrastructure.configuration;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "fr.epita.mti.jee.infrastructure.persistence.repository")
@EntityScan(basePackages = "fr.epita.mti.jee.infrastructure.persistence.entity")
public class SpringJpaConfiguration {}