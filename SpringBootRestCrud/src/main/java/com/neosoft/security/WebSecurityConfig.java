package com.neosoft.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.exception.CustomAuthenticationEntryPoint;
import com.neosoft.exception.GlobalExceptionHandler;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private GlobalExceptionHandler globalExceptionHandler;
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/students/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/students").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/addStudent").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
                //.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and()
                .and().exceptionHandling().accessDeniedPage("/403").and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
                
		/*
		 * http.httpBasic() .and() .authorizeRequests() .antMatchers(HttpMethod.GET,
		 * "/students/*") .hasRole("ADMIN") .antMatchers(HttpMethod.POST, "/addStudent")
		 * .hasAnyRole("USER","ADMIN").anyRequest().authenticated() .and() .csrf()
		 * .disable().headers().frameOptions().disable();
		 */                
                

    }  
    
 // In-memory authentication to authenticate the user i.e. the user credentials are stored in the memory.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.inMemoryAuthentication().withUser("user")
        .password(passwordEncoder().encode("user123")).roles("USER")
    	.and().withUser("admin")
        .password(passwordEncoder().encode("admin123")).roles("ADMIN");
    }
    
    @Bean
    @Primary
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//      auth.inMemoryAuthentication()
//        .withUser("user").password("{noop}user").roles("USER")
//        .and()
//        .withUser("admin").password("{noop}admin").roles("ADMIN");
//    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, ex) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            ServletOutputStream out = response.getOutputStream();
            new ObjectMapper().writeValue(out, new CustomAuthenticationEntryPoint());
            out.flush();
        };
    }   
    

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
    	 return new CustomAuthenticationEntryPoint();
    }
    
}