package com.ifba.SISQuiosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifba.SISQuiosque.entity.PapelEntity;

import java.util.Optional;

public interface PapelRepository extends JpaRepository<PapelEntity, Long> {

    Optional<PapelEntity> findByPapel(String papel);

}
