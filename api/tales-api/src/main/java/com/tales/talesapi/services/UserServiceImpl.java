package com.tales.talesapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tales.talesapi.dto.UserDto;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.UserService;
import com.tales.talesapi.repositories.UsuarioRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UsuarioRepository userRepository;
//    private RoleRepository roleRepository;
  
	
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UsuarioRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @JsonProperty
    public void saveUser(UserDto userDto) {
        Usuario user = new Usuario();
        user.setNome(userDto.getNome());
        user.setEmail(userDto.getEmail());
        user.setMatricula(userDto.getMatricula());
        // encrypt the password using spring security
        user.setSenha(passwordEncoder.encode(userDto.getSenha()));

//        Role role = roleRepository.findByName("ROLE_ADMIN");
//        if(role == null){
//            role = checkRoleExist();
//        }
//        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    public Usuario findUserByMatricula(String matricula) {
        return userRepository.findByMatricula(matricula);
    }

    public List<Usuario> findAllUsers() {
        List<Usuario> users = userRepository.findAll();
        return users;
    }

	@Override
	public Usuario findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

//    private Usuario mapToUserDto(Usuario user){
//        Usuario userDto = new Usuario();
//        String[] str = user.getNome().split(" ");
//        userDto.setNome(str[0]);
//        userDto.setEmail(user.getEmail());
//        return userDto;
//    }
//}
//    private Role checkRoleExist(){
//        Role role = new Role();
//        role.setName("ROLE_ADMIN");
//        return roleRepository.save(role);
//}
}
