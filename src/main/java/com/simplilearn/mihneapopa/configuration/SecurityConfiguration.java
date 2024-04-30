package com.simplilearn.mihneapopa.configuration;

import com.simplilearn.mihneapopa.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserServiceImpl userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/product/getAll").hasAnyRole("CUSTOMER", "ADMIN")
                                .requestMatchers("/order/getAll").hasAnyRole("CUSTOMER", "ADMIN")
                                .requestMatchers("admin/product/getAll").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(form ->
                        form.loginPage("/login")
                                .successForwardUrl("/home")
                                .loginProcessingUrl("/authenticateUser")
                                .permitAll())
                .logout(LogoutConfigurer::permitAll);

        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}