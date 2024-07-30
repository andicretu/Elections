package projects.Elections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import projects.Elections.Models.CandidateModel;
import projects.Elections.Models.ElectorModel;
import projects.Elections.Repositories.CandidateRepository;
import projects.Elections.Repositories.ElectionsRepository;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final ElectionsRepository electionsRepository;
    private final CandidateRepository candidateRepository;

    public WebSecurityConfig(ElectionsRepository electionsRepository, CandidateRepository candidateRepository) {
        this.electionsRepository = electionsRepository;
        this.candidateRepository = candidateRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/welcome", "/register", "/elections/show-candidate/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/elections/show-elector", true)
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
            CandidateModel candidateModel = candidateRepository.findCandidateByElectorId(electorModel.getId());
            return new org.springframework.security.core.userdetails.User(
                    electorModel.getEmail(),
                    electorModel.getPassword(),
                    List.of(candidateModel == null
                            ? new SimpleGrantedAuthority("ELECTOR ROLE")
                            : new SimpleGrantedAuthority("CANDIDATE ROLE"))
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