package com.tales.talesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tales.talesapi.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

}
