package com.tales.talesapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class WebConfig {

	 @Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity HttpSec) throws Exception {
	     HttpSec.csrf(csrf -> csrf.disable())
	    			  .sessionManagement(
	    					  sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    					  ).authorizeHttpRequests(authzH -> authzH
	    					   .requestMatchers(HttpMethod.POST, "/*/auth/login").permitAll()
//	    					   .requestMatchers(HttpMethod.POST, "/login").permitAll()
	    					   .anyRequest().authenticated()
	    			 );
	     return HttpSec.build();
	    }
	    
	 @Bean
	 AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
	    	return authConfig.getAuthenticationManager();
	 }
	    
	 @Bean
	 PasswordEncoder passEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
}
