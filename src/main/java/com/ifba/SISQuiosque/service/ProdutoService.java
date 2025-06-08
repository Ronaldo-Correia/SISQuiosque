package com.ifba.SISQuiosque.service;

import java.util.List;
import com.ifba.SISQuiosque.dto.ProdutoDTO;
import com.ifba.SISQuiosque.entity.Produto;

public interface ProdutoService {


    ProdutoDTO buscarPorId(Long id);

    ProdutoDTO salvar(ProdutoDTO produtoDTO);

    ProdutoDTO atualizar(Long id, ProdutoDTO produtoDTO);

    void deletar(Long id);

List<ProdutoDTO> listarTodos();
}