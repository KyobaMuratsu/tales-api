package com.tales.talesapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tales.talesapi.security.BearerTokenWrapper;

@EnableWebSecurity
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
      			.allowedHeaders("*")
      			.allowedMethods("*")
      			.allowedOrigins("*");
    }
    
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//      registry.addInterceptor(bearerTokenInterceptor());
//    }
//
//    @Bean
//    public BearerTokenInterceptor bearerTokenInterceptor() {
//      return new BearerTokenInterceptor(bearerTokenWrapper());
//    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public BearerTokenWrapper bearerTokenWrapper() {
      return new BearerTokenWrapper();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity HttpSec) throws Exception {
    	
    	return HttpSec.csrf(csrf -> csrf.disable())
    							.sessionManagement(
    									sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    							).authorizeHttpRequests(authzH -> authzH
    									.requestMatchers(HttpMethod.POST, "/login").permitAll()
    									
    							).build();
    }
    
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
    	return authConfig.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
}

