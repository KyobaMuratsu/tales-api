package com.tales.talesapi.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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
import com.tales.talesapi.entities.Tag;
import com.tales.talesapi.entities.Usuario;
import com.tales.talesapi.repositories.TagRepository;
import com.tales.talesapi.repositories.UserService;
import com.tales.talesapi.repositories.UsuarioRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UsuarioRepository userRepository;
//    private RoleRepository roleRepository;
	
	@Autowired
	private TagRepository tagRepo;
	
    private PasswordEncoder passwordEncoder;
    
    private final String uploadDirectory = "C:\\Users\\bruno\\OneDrive\\Área de Trabalho\\tales-app\\tales-app\\public\\Imagens";

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
    public void savePostagem(String usuarioId, PostagemDto postagemDto, MultipartFile imagem, List<String> tags) throws IOException {
        Usuario usuario = userRepository.findByMatricula(usuarioId);

        if (usuario == null) {
            System.out.println("Usuário não encontrado com ID: " + usuarioId);
            return;
        }

        String imagemUrl = saveImagem(imagem);

        Postagens postagem = new Postagens(postagemDto.getTextoPostagem(), imagemUrl, LocalDateTime.now());
        postagem.setUsuario(usuario);

        for (String tagName : tags) {
            Tag tag = tagRepo.findByNome(tagName);

            if (tag != null) {
                postagem.addTag(tag);
            } else {
                System.out.println("Tag não encontrada com nome: " + tagName);
            }
        }

        usuario.getPostagens().add(postagem);
        userRepository.save(usuario);
    }

	@Override
	public void updateUser(String matricula, MultipartFile picUrl, String nome, String email, List<String> tags) throws IOException {
	    Usuario user = userRepository.findByMatricula(matricula);

	    if (user == null) {
	        System.out.println("Usuário não encontrado com matrícula: " + matricula);
	        return;
	    }

	    String imagemUrl = saveImagem(picUrl);

	    user.setPicUrl(imagemUrl);
	    user.setNome(nome);
	    user.setEmail(email);

	    user.getTags().clear();

	    for (String tagName : tags) {
	        Tag tag = tagRepo.findByNome(tagName);

	        if (tag != null) {
	            user.addTag(tag);
	        } else {
	            System.out.println("Tag não encontrada com nome: " + tagName);
	        }
	    }

	    userRepository.save(user);
	}

    public Set<Tag> getTagByUserId(String userId) {
        Usuario usuario = userRepository.findByMatricula(userId);

        if (usuario != null) {
            Set<Tag> tags = usuario.getTags();
            return tags;
        }

        return Collections.emptySet(); // Retorna um conjunto vazio se o usuário não for encontrado
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
