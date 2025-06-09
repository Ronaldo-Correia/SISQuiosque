package com.ifba.SISQuiosque.dto;
import lombok.Data;

@Data
public class CardapioItemDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String imagemUrl;
}