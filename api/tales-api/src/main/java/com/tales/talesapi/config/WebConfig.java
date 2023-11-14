package com.tales.talesapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@EnableWebSecurity
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private UserDetailsService userDetailsService;
	
	
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//      registry.addMapping("/**")
//      			.allowedHeaders("*")
//      			.allowedMethods("*")
//      			.allowedOrigins("*");
//    }
    
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//      registry.addInterceptor(bearerTokenInterceptor());
//    }
//
//    @Bean
//    public BearerTokenInterceptor bearerTokenInterceptor() {
//      return new BearerTokenInterceptor(bearerTokenWrapper());
//    }

//    @Bean
//    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public BearerTokenWrapper bearerTokenWrapper() {
//      return new BearerTokenWrapper();
//    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity HttpSec) throws Exception {
    	
    	return HttpSec.csrf(csrf -> csrf.disable())
    							.sessionManagement(
    									sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    							).authorizeHttpRequests(authzH -> authzH
    									.requestMatchers("/**").permitAll()
    									.requestMatchers("/index/**").permitAll()
    									.requestMatchers("/login/**").permitAll() 	
    									.requestMatchers("/register/**").permitAll()
    									.requestMatchers(HttpMethod.POST, "register/save").permitAll()
    									.requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
    									.requestMatchers(HttpMethod.GET, "/users").permitAll()
    							).build();
    }
    
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
    	return authConfig.getAuthenticationManager();
    }
    
    @Bean
    public static PasswordEncoder passEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passEncoder());
    }
    
    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Nome da API")
                        .description("Descrição da API")
                        .version("0.0.1") //você sabe como funciona uma versão?
                        .contact(new Contact()
                                .name("Nome do DEV")
                                .email("email@do.dev")))
                .externalDocs(new ExternalDocumentation()
                        .description("Algum link externo")
                        .url("https:/wiki...."));
    }
    
}

