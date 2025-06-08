package com.ifba.SISQuiosque.mapping;
import org.springframework.stereotype.Component;

import com.ifba.SISQuiosque.dto.ProdutoDTO;
import com.ifba.SISQuiosque.entity.Produto;

@Component
public class ProdutoMapper {

    public ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setImagemUrl(produto.getImagemUrl());
        return dto;
    }

    public Produto toEntity(ProdutoDTO dto) {
        Produto p = new Produto();
        p.setId(dto.getId());
        p.setNome(dto.getNome());
        p.setDescricao(dto.getDescricao());
        p.setPreco(dto.getPreco());
        p.setImagemUrl(dto.getImagemUrl());
        return p;
    }
}