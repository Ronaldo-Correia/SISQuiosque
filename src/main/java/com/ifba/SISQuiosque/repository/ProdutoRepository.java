package com.ifba.SISQuiosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifba.SISQuiosque.entity.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}