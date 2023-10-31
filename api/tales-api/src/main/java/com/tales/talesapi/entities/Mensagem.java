package com.tales.talesapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Mensagem {
	
	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
}
