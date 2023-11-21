package com.tales.talesapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.tales.talesapi.dto.LoginDto;
import com.tales.talesapi.dto.TokenDto;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.UserService;
import com.tales.talesapi.services.TokenService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private UserService userService;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService token;

	@PostMapping("/login")
	public List<String> login(@RequestBody LoginDto login) {
		ArrayList<String> TokenAndUser = new ArrayList<>();
		UsernamePasswordAuthenticationToken userPassAuthToken =
				new UsernamePasswordAuthenticationToken(login.getLogin(),
														login.getPassword());
		
		Authentication auth = this.authManager.authenticate(userPassAuthToken);
		var user = (Usuario) auth.getPrincipal();
		
		TokenDto dto = new TokenDto();
		dto.setToken(token.gerarToken(user));
		
		TokenAndUser.add(dto.getToken());
		TokenAndUser.add(login.getLogin());
		
		return TokenAndUser;
	}

    // handler method to handle list of users
    @GetMapping("/check-token")
    public String checkToken(HttpServletRequest request){
        String authorization = request.getHeader("Accept");
        return authorization;
//        if(authorization.isEmpty()) {
//        	return "401";
//        }
//        
//        if(authorization.split(" ")[0] != "Bearer") {
//        	return "500";
//        }
//        
//    	String lettoken = authorization.split(" ")[1];
//    	String status = "false";
//    	
//    	if(!lettoken.isEmpty()) {
//    		try {
//				boolean tokenVerified = token.validateJwtToken(lettoken);
//				if(tokenVerified) {
//					status = "true";
//					return status;
//				}else
//					status = "false";
//					return status;
//			} catch (Exception e) {
//				System.out.println(e);
//			}
//    	}
//    	
//		return status;
        
    }
}
