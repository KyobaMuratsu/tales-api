package com.tales.talesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tales.talesapi.dto.UserDto;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RegisterController {
	
	@Autowired
    private UserService userService;
	

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        Usuario user = new Usuario();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@RequestBody UserDto userDto,
                                Model model,
                                BindingResult result) {
        // Check if password is provided
        if (userDto.getSenha() == null || userDto.getSenha().isEmpty()) {
            result.rejectValue("senha", null, "Password cannot be null or empty");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        
        userService.saveUser(userDto);
        
        
        return "redirect:/register?success";
    }

}
