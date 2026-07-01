package fr.epita.mti.jee.infrastructure.configuration;


import fr.epita.mti.jee.domain.exceptions.user.UserNotFoundException;
import fr.epita.mti.jee.infrastructure.persistence.entities.UserJpaEntity;
import fr.epita.mti.jee.infrastructure.persistence.repository.user.UserRepositoryJPA;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityConfiguration {

    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public CustomSecurityConfiguration(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        return http.csrf(csrf -> csrf.ignoringRequestMatchers("/database/**", "/**"))

            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))

            .formLogin(login -> login.usernameParameter("email").passwordParameter("motDePasse"))

            .httpBasic(basic -> basic.authenticationEntryPoint((request, response, authException) -> {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }))

            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/error").permitAll()
                .requestMatchers("/database/**", "/swagger/**").permitAll()

                .requestMatchers("/api-edition/**").hasRole("ORGANIZER")

                .requestMatchers("/api-zombie/histogram/").permitAll()
                .requestMatchers("/api-zombie/**").hasRole("USER")

                .requestMatchers("/api-runner/**").hasRole("USER")

                .requestMatchers("/api-user/user").permitAll()
                .requestMatchers("/api-user/users").hasRole("ORGANIZER")
                .requestMatchers("/api-user/**").hasRole("USER")

                .anyRequest().authenticated())

            .build();
    }

    @Bean
    public UserDetailsService user() {
        return username -> {
            UserJpaEntity userEntity = userRepositoryJPA
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

            return User
                .builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole())
                .build();
        };
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
