package com.tales.talesapi.entities;

import java.time.LocalDateTime;
import java.util.Set;

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

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String textoComentario;
	
	private LocalDateTime criadoEm;
	
	//Rever
//	@OneToMany(mappedBy = "Usuario", fetch = FetchType.LAZY)
//	private Set<Usuario> usuario;
	
	//Rever
	@ManyToOne
	@JoinColumn(name = "postagens_id")
	private Postagens postagens;
	
}
