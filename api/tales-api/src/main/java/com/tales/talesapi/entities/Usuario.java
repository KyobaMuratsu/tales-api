package com.tales.talesapi.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(unique = true)
	private String matricula;
	
	private String email;
	
	private String senha;
	
	private String nome;
	
	//Enum provavelmente
	private String curso;
	
	private String picUrl;
	
	private String biografia;
	
	private LocalDateTime dataEntrada;
	
	private LocalDateTime dataNascimento;
	
	private LocalDateTime criadoEm;
	
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Postagens> postagens;

	//Rever
	@ManyToOne
	@JoinColumn(name = "comentario_id")
	private Comentario comentario;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return matricula;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Usuario(String matricula, String email, String senha, String nome, String curso, String picUrl,
			String biografia, LocalDateTime dataEntrada, LocalDateTime dataNascimento) {
		super();
		this.matricula = matricula;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.curso = curso;
		this.picUrl = picUrl;
		this.biografia = biografia;
		this.dataEntrada = dataEntrada;
		this.dataNascimento = dataNascimento;
	}
	
	
	
	
}
