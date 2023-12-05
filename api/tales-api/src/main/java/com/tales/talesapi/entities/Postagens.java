package com.tales.talesapi.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Postagens {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String textoPostagem;
	
	private String imagemUrlPostagem;
	
	private LocalDateTime criadoEm;
	
	public Postagens(String textoPostagem, String imagemUrlPostagem, LocalDateTime criadoEm) {
		super();
		this.textoPostagem = textoPostagem;
		this.imagemUrlPostagem = imagemUrlPostagem;
		this.criadoEm = criadoEm;
	}

	//Rever
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToMany
	@JoinTable(
			name = "publicacao_tags",
			joinColumns = @JoinColumn(name = "publicacao_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id")
			)
	private Set<Tag> tags = new HashSet<>();
	
    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getPost().add(this);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getPost().remove(this);
    }
	
	
	@OneToOne
	private Notificacao notificacao;
	
}
