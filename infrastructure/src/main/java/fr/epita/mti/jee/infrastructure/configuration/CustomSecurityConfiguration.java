package fr.epita.mti.jee.infrastructure.configuration;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class CustomSecurityConfiguration {

    public DataSource datasource;

    public CustomSecurityConfiguration(DataSource datasource) {
        this.datasource = datasource;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.ignoringRequestMatchers("/database/**", "/**"))

            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))

            .formLogin(login -> login.usernameParameter("email").passwordParameter("motDePasse"))

            .httpBasic(basic -> basic.authenticationEntryPoint((request, response, authException) -> {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }))

            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/**")
                .permitAll()
                .requestMatchers("/login", "/error")
                .permitAll()
                .requestMatchers("/database/**", "/swagger/**")
                .permitAll()
                .requestMatchers("/api-edition/**")
                .hasRole("ORGANISATEUR")
                .requestMatchers("/api-zombie/**")
                .hasRole("UTILISATEUR")
                .requestMatchers("/api-user/**")
                .permitAll()
                .anyRequest()
                .authenticated())

            .build();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(datasource);
        manager.setUsersByUsernameQuery("select username,password,enabled " +
                                        "from users " +
                                        "where username = ?");
        return manager;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
