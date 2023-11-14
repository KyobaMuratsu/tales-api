package com.tales.talesapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tales.talesapi.dto.UserDto;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
	
	// handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "home";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }

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

        Usuario existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }


    // handler method to handle list of users
    @GetMapping("/users")
    public List<Usuario> users(){
        List<Usuario> users = userService.findAllUsers();
        return users;
    }
}
