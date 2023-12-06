package com.tales.talesapi.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 6)
	private String nome;
	
	//Rever
	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	private Set<Usuario> usuario = new HashSet<>();
	
	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	private Set<Postagens> post = new HashSet<>();
	
	
}
