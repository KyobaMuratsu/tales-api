package com.tales.talesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tales.talesapi.entities.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

}
