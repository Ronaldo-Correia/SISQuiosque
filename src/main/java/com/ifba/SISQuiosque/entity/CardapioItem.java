package com.ifba.SISQuiosque.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cardapio_itens")
public class CardapioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    private Double preco;
    
    @Column(nullable = false)
    private String imagemUrl;
    
    @Column(nullable = false)
    private Boolean ativo = true;
}