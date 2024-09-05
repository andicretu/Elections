package projects.Elections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Repositories.ElectionsRepository;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final ElectionsRepository electionsRepository;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    public WebSecurityConfig(ElectionsRepository electionsRepository) {
        this.electionsRepository = electionsRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/welcome", "/registerElector", "/register-candidate","/show-candidate/**", "/showResults").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            ElectorModel electorModel = electionsRepository.findByEmail(email);
            if (electorModel == null) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
            return new org.springframework.security.core.userdetails.User(
                    electorModel.getEmail(),
                    electorModel.getPassword(),
                    Collections.emptyList()
            );
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
}
