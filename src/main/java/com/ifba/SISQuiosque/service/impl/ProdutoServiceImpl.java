package com.ifba.SISQuiosque.service.impl;

import com.ifba.SISQuiosque.dto.ProdutoDTO;
import com.ifba.SISQuiosque.entity.Produto;
import com.ifba.SISQuiosque.repository.ProdutoRepository;
import com.ifba.SISQuiosque.service.ProdutoService;
import org.springframework.stereotype.Service;
import com.ifba.SISQuiosque.mapping.ProdutoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoServiceImpl(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
public List<ProdutoDTO> listarTodos() {
    List<Produto> produtos = repository.findAll();
    return produtos.stream()
                   .map(mapper::toDTO)
                   .collect(Collectors.toList());
}

    @Override
    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
        return mapper.toDTO(produto);
    }

    @Override
    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto produto = mapper.toEntity(dto);
        Produto salvo = repository.save(produto);
        return mapper.toDTO(salvo);
    }

    @Override
    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        Produto produtoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));

        produtoExistente.setNome(dto.getNome());
        produtoExistente.setDescricao(dto.getDescricao());
        produtoExistente.setPreco(dto.getPreco());
        produtoExistente.setImagemUrl(dto.getImagemUrl());

        Produto atualizado = repository.save(produtoExistente);
        return mapper.toDTO(atualizado);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
    
}