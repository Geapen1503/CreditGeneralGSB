package cgb.transfert;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain; 


@Configuration
public class SecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(
                (requests) -> requests.anyRequest().authenticated()  
            )
            .httpBasic(Customizer.withDefaults()) 
            .headers().frameOptions().disable(); 

        return http.build();
    }

}
