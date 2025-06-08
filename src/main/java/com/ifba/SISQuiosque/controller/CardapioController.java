package com.ifba.SISQuiosque.controller;

import com.ifba.SISQuiosque.dto.ProdutoDTO;
import com.ifba.SISQuiosque.entity.Produto;
import com.ifba.SISQuiosque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.ifba.SISQuiosque.service.ProdutoService;



import java.util.List;

@Controller
public class CardapioController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/cardapio")
    public String mostrarCardapio(Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        model.addAttribute("total", 0.0);
        return "cardapio.jte";
    }

    @GetMapping("/cadastrar-pedido")
    public String cadastrarPedido() {
        return "cadastrarPedido.jte";
    }
}
