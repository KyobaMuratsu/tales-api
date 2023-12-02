package com.tales.talesapi.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tales.talesapi.dto.PostagemDto;
import com.tales.talesapi.dto.UserDto;
import com.tales.talesapi.entities.Postagens;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.UserService;
import com.tales.talesapi.repositories.UsuarioRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UsuarioRepository userRepository;
//    private RoleRepository roleRepository;
	
    private PasswordEncoder passwordEncoder;
    
    private final String uploadDirectory = "C:/Users/bruno/OneDrive/Área de Trabalho/Projeto/tales-api/banco-de-dados/imagens/";

    public UserServiceImpl(UsuarioRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @JsonProperty
    public void saveUser(UserDto userDto) {
        Usuario user = new Usuario();
        List<Postagens> postagens = new ArrayList<>();
        user.setMatricula(userDto.getMatricula());
        // encrypt the password using spring security
        user.setSenha(passwordEncoder.encode(userDto.getSenha()));
        user.setCriadoEm(LocalDateTime.now());
        
        user.setPostagens(postagens);

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
	
    private String saveImagem(MultipartFile imagem) throws IOException {
        Path uploadPath = Paths.get(uploadDirectory).toAbsolutePath().normalize();
        String nomeArquivo = "imagem_" + System.currentTimeMillis() + ".jpg";
        Path filePath = uploadPath.resolve(nomeArquivo);

        // Salve o arquivo no sistema de arquivos
        File file = new File(filePath.toString());
        imagem.transferTo(file);

        return filePath.toString();
    }

	@Override
	public void savePostagem(String usuarioId, PostagemDto postagemDto, MultipartFile imagem) throws IOException {
	       Usuario usuario = userRepository.findByMatricula(usuarioId);

	        // Lide com a imagem aqui, salve-a no sistema de arquivos ou em um serviço de armazenamento em nuvem, etc.
	        String imagemUrl = saveImagem(imagem);

	        // Crie a postagem
	        Postagens postagem = new Postagens(postagemDto.getTextoPostagem(), imagemUrl, LocalDateTime.now());
	        postagem.setUsuario(usuario);

	        // Adicione a postagem ao usuário e salve no banco de dados
	        usuario.getPostagens().add(postagem);
	        userRepository.save(usuario);
		
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
