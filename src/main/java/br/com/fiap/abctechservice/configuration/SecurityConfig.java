package br.com.fiap.abctechservice.configuration;

import br.com.fiap.abctechservice.security.JwtAuthenticationEntrypoint;
import br.com.fiap.abctechservice.security.JwtRequestFilter;
import br.com.fiap.abctechservice.security.JwtUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntrypoint jwtAuthenticationEntrypoint;
    private final JwtUserDetailService jwtUserDetailService;
    private final JwtRequestFilter jwtFilter;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(JwtAuthenticationEntrypoint jwtAuthenticationEntrypoint,
                          JwtUserDetailService jwtUserDetailService,
                          JwtRequestFilter jwtFilter,
                          PasswordEncoder passwordEncoder) {
        this.jwtAuthenticationEntrypoint = jwtAuthenticationEntrypoint;
        this.jwtUserDetailService = jwtUserDetailService;
        this.jwtFilter = jwtFilter;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/users").permitAll()
                .antMatchers("/users/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntrypoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .formLogin().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager()
            throws Exception {
        return super.authenticationManager();
    }
}
