//package com.tales.talesapi.security;
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//public class SecurityConfig {
//
//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//     http
////        		.cors(x -> x.configurationSource(corsConfigurationSource()))
//                .csrf(x -> x.disable())
//                .authorizeHttpRequests(x -> x.anyRequest().permitAll());
//
//        return http.build();
//    }
//    
////	@Bean
////	CorsConfigurationSource corsConfigurationSource() {
////		CorsConfiguration configuration = new CorsConfiguration();
////		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:3000"));
////		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
////		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////		source.registerCorsConfiguration("/**", configuration);
////		return source;
////	}
//}
